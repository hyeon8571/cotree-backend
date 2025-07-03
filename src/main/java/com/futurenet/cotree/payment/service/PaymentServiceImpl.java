package com.futurenet.cotree.payment.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointSaveRequest;
import com.futurenet.cotree.greenpoint.service.GreenPointService;
import com.futurenet.cotree.item.repository.ItemRepository;
import com.futurenet.cotree.order.constant.OrderStatus;
import com.futurenet.cotree.order.domain.Order;
import com.futurenet.cotree.order.domain.OrderItem;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.repository.OrderItemRepository;
import com.futurenet.cotree.order.repository.OrderRepository;
import com.futurenet.cotree.order.service.exception.OrderErrorCode;
import com.futurenet.cotree.order.service.exception.OrderException;
import com.futurenet.cotree.payment.dto.request.PaymentConfirmRequest;
import com.futurenet.cotree.payment.dto.request.PaymentRegisterRequest;
import com.futurenet.cotree.payment.dto.request.PaymentRequest;
import com.futurenet.cotree.payment.dto.response.ItemPriceAndIsEcoResponse;
import com.futurenet.cotree.payment.repository.PaymentRepository;
import com.futurenet.cotree.payment.service.exception.PaymentErrorCode;
import com.futurenet.cotree.payment.service.exception.PaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final GreenPointService greenPointService;
    private final OrderItemRepository orderItemRepository;

    /**
     * 결제 기능: 주문이 성공하면 실행된다. 역할 분리와 성능을 위해 비동기로 처리한다.
     * 1. 주문한 상품의 아이디를 이용하여 상품 정보를 찾는다.
     * 2. 결제할 가격을 계산하고 그린 포인트 적립을 위해 친환경 상품 결제 가격을 계산
     * 3. 그린 포인트 저장
     * 4. 주문 상태를 PENDING에서 PAID로 업데이트
     *
     * @param paymentRequest 주문 정보, 카드 및 은행 정보, 가격 계산 및 포인트 계산을 위한 주문 상품 목록
     * */
    @Override
    @Transactional
    public void pay(PaymentRequest paymentRequest) {

        List<OrderItemRegisterRequest> orderItems = paymentRequest.getOrderItems();

        long price = 0;
        long greenPrice = 0;

        List<Long> itemIds = orderItems.stream()
                .map(OrderItemRegisterRequest::getItemId)
                .toList();

        List<ItemPriceAndIsEcoResponse> itemInfos = itemRepository.getItemPriceAndIsEcoByIds(itemIds);

        Map<Long, ItemPriceAndIsEcoResponse> itemInfoMap = itemInfos.stream()
                .collect(Collectors.toMap(ItemPriceAndIsEcoResponse::getId, Function.identity()));


        for (OrderItemRegisterRequest item : orderItems) {
            ItemPriceAndIsEcoResponse info = itemInfoMap.get(item.getItemId());

            if (info == null) {
                throw new OrderException(OrderErrorCode.ORDER_ITEM_NOT_FOUND);
            }

            int unitPrice = info.getPrice() - info.getDiscount();
            long totalPrice = (long) unitPrice * item.getQuantity();

            price += totalPrice;

            if ("Y".equals(info.getIsGreen())) {
                greenPrice += totalPrice;
            }
        }

        PaymentRegisterRequest paymentRegisterRequest = PaymentRegisterRequest.of(paymentRequest, price);
        int paymentResult = paymentRepository.savePayment(paymentRegisterRequest);

        if (paymentResult == 0) {
            throw new PaymentException(PaymentErrorCode.PAYMENT_FAIL);
        }

        if (greenPrice != 0) {
            greenPointService.savePoint(GreenPointSaveRequest.of(paymentRequest.getMemberId(), paymentRegisterRequest.getPaymentId(), greenPrice));
        }

        int updateOrderStatusResult = orderRepository.updateOrderStatus(paymentRequest.getOrderId(), OrderStatus.SUCCESS.getStatus());

        if (updateOrderStatusResult == 0) {
            throw new OrderException(OrderErrorCode.ORDER_STATUS_UPDATE_FAIL);
        }
    }

    @Override
    @Transactional
    public void payConfirm(PaymentConfirmRequest paymentConfirmRequest, Long memberId) {

        Order order = orderRepository.getOrderByOrderNumberForPayConfirm(paymentConfirmRequest.getOrderNumber());
        List<OrderItem> orderItems = orderItemRepository.getOrderItemsByOrderId(order.getId());

        List<OrderItemRegisterRequest> orderItemRegisterRequests =
                orderItems.stream()
                        .map(OrderItemRegisterRequest::from)
                        .toList();

        pay(PaymentRequest.of(memberId, paymentConfirmRequest, order, orderItemRegisterRequests));
    }
}
