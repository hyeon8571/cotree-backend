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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ItemResponse> getTodayItems() {
        List<Item> itemList = itemRepository.getTodayItems();
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void bulkDecreaseStock(List<OrderItemRegisterRequest> orderItemRegisterRequests) {

        int result = itemRepository.bulkDecreaseStock(orderItemRegisterRequests);

        if (result != orderItemRegisterRequests.size()) {
            throw new ItemException(ItemErrorCode.ITEM_QUANTITY_LACK);
        }
    }

    @Override
    @Cacheable("eventItemsCache")
    @Transactional
    public List<ItemResponse> getEventItems() {
        List<Item> itemList = itemRepository.getEventItems();
        return itemList.stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
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
}
