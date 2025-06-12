package com.futurenet.cotree.payment.service;

import com.futurenet.cotree.greenpoint.dto.GreenPointSaveRequest;
import com.futurenet.cotree.greenpoint.service.GreenPointService;
import com.futurenet.cotree.item.repository.ItemRepository;
import com.futurenet.cotree.item.service.exception.ItemErrorCode;
import com.futurenet.cotree.item.service.exception.ItemException;
import com.futurenet.cotree.order.constant.OrderStatus;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.order.repository.OrderRepository;
import com.futurenet.cotree.order.service.exception.OrderErrorCode;
import com.futurenet.cotree.order.service.exception.OrderException;
import com.futurenet.cotree.payment.dto.request.PaymentRegisterRequest;
import com.futurenet.cotree.payment.dto.request.PaymentRequest;
import com.futurenet.cotree.payment.dto.response.ItemPriceAndIsEcoResponse;
import com.futurenet.cotree.payment.repository.PaymentRepository;
import com.futurenet.cotree.payment.service.exception.PaymentErrorCode;
import com.futurenet.cotree.payment.service.exception.PaymentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final GreenPointService greenPointService;

    @Override
    @Transactional
    public void pay(PaymentRequest paymentRequest) {

        // TODO: 반복문 내의 단발성 쿼리 Bulk 처리

        int price = 0;
        int greenPrice = 0;

        for (OrderItemRegisterRequest item : paymentRequest.getOrderItems()) {
            ItemPriceAndIsEcoResponse itemPriceAndIsEcoResponse = itemRepository.getItemPriceAndIsEcoById(item.getItemId());
            price += (itemPriceAndIsEcoResponse.getPrice() - itemPriceAndIsEcoResponse.getDiscount()) * item.getQuantity();
            if (itemPriceAndIsEcoResponse.getIsGreen().equals("Y")) {
                greenPrice += (itemPriceAndIsEcoResponse.getPrice() - itemPriceAndIsEcoResponse.getDiscount()) * item.getQuantity();
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
}
