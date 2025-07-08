package com.futurenet.cotree.item.service;

import com.futurenet.cotree.auth.security.dto.UserPrincipal;
import com.futurenet.cotree.history.dto.request.MemberActionRequestEvent;
import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.dto.response.ItemDetailResponse;
import com.futurenet.cotree.item.dto.response.ItemResponse;
import com.futurenet.cotree.item.repository.ItemRepository;
import com.futurenet.cotree.item.service.exception.ItemErrorCode;
import com.futurenet.cotree.item.service.exception.ItemException;
import com.futurenet.cotree.member.dto.response.MemberGenderAgeResponse;
import com.futurenet.cotree.member.repository.MemberRepository;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.futurenet.cotree.global.constant.PaginationConstants.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public List<ItemResponse> getItemsByCategory(Long categoryId, int page) {
        int start = (page - 1) * PAGE_SIZE;

        List<Item> itemList = itemRepository.getItemsByCategory(categoryId, start, PAGE_SIZE);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItemDetailResponse getItemDetail(UserPrincipal userPrincipal, Long itemId) {
        if (userPrincipal != null) {
            saveMemberActionLog(userPrincipal.getId(), itemId, null);
        }
        return ItemDetailResponse.from(itemRepository.getItemDetailById(itemId));
    }

    @Override
    @Transactional
    public List<ItemResponse> getEcoItems(int page) {
        int start = (page - 1) * PAGE_SIZE;
        List<Item> itemList = itemRepository.getEcoItems(start, PAGE_SIZE);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemResponse> searchItems(UserPrincipal userPrincipal, String keyword, Long categoryId, int page, String isGreen) {
        if (userPrincipal != null) {
            saveMemberActionLog(userPrincipal.getId(), null, keyword);
        }

        int start = (page - 1) * PAGE_SIZE;
        List<Item> itemList = itemRepository.searchItems(keyword, categoryId, start, PAGE_SIZE, isGreen);
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ItemResponse> getTodayItems() {
        List<Item> itemList = itemRepository.getTodayItems();
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void bulkDecreaseStockWithLock(List<OrderItemRegisterRequest> orderItemRegisterRequests) {

        List<Long> itemIds = orderItemRegisterRequests.stream()
                .map(OrderItemRegisterRequest::getItemId)
                .sorted()
                .toList();

        itemRepository.lockItemsInOrder(itemIds);

        int result = itemRepository.bulkDecreaseStock(orderItemRegisterRequests);

        if (result != orderItemRegisterRequests.size()) {
            throw new ItemException(ItemErrorCode.ITEM_QUANTITY_LACK);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<OrderItemRegisterRequest> orderItemRegisterRequests) {
        List<OrderItemRegisterRequest> sortedItems = new ArrayList<>(orderItemRegisterRequests);
        sortedItems.sort(Comparator.comparing(OrderItemRegisterRequest::getItemId));

        for (OrderItemRegisterRequest orderItemRegisterRequest : sortedItems) {
            int updated = itemRepository.decreaseStock(orderItemRegisterRequest);

            if (updated == 0) {
                throw new ItemException(ItemErrorCode.ITEM_QUANTITY_LACK);
            }
        }
    }

    @Override
    @Transactional
    public void bulkDecrease(List<OrderItemRegisterRequest> orderItemRegisterRequests) {
        int updated = itemRepository.bulkDecreaseStock(orderItemRegisterRequests);
        if (updated == 0) {
            throw new ItemException(ItemErrorCode.ITEM_QUANTITY_LACK);
        }
    }

    private void saveMemberActionLog(Long memberId, Long itemId, String keyword) {
        MemberGenderAgeResponse info = memberRepository.getMemberGenderAge(memberId);
        MemberActionRequestEvent event;

        //age가 DB상에서 String로 되어있어서 int형식으로 변경
        int age = Integer.parseInt(info.getAge().replace("s", ""));

        if (itemId != null) {
            event = new MemberActionRequestEvent(memberId, info.getGender(), age, itemId);
        }
        else{
            event = new MemberActionRequestEvent(memberId, info.getGender(),age, keyword);
        }
        eventPublisher.publishEvent(event);
    }

    @Override
    @Transactional
    public void decreaseItemQuantitiesWithLock(List<OrderItemRegisterRequest> orderItems) {
        List<Long> itemIds = orderItems.stream()
                .map(OrderItemRegisterRequest::getItemId)
                .collect(Collectors.toList());

        itemRepository.lockItemsByIds(itemIds);

        int updatedCount = itemRepository.batchDecreaseQuantities(orderItems);

        if (updatedCount != orderItems.size()) {
            throw new IllegalStateException("재고 부족 또는 차감 실패");
        }
    }
}

