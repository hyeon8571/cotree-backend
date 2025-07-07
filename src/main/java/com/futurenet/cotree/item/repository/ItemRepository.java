package com.futurenet.cotree.item.repository;

import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.domain.ItemDetail;
import com.futurenet.cotree.order.dto.request.OrderItemRegisterRequest;
import com.futurenet.cotree.payment.dto.response.ItemPriceAndIsEcoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemRepository {
    List<Item> getItemsByCategory(@Param("categoryId") Long categoryId, @Param("start") int start, @Param("size") int size);
    ItemDetail getItemDetailById(@Param("id") Long id);
    List<Item> getEcoItems(@Param("start") int start, @Param("size") int size);
    List<Item> searchItems(@Param("keyword")String keyword, @Param("categoryId") Long categoryId, @Param("start") int start, @Param("size") int size, @Param("isGreen") String isGreen);
    long getTotalItemCount();
    long getTotalEcoItemCount();
    List<Item> getTodayItems();
    int bulkDecreaseStock(@Param("request")List<OrderItemRegisterRequest> request);
    int decreaseStock(@Param("request") OrderItemRegisterRequest request);
    List<Long> lockItemsInOrder(@Param("ids") List<Long> ids);
    List<ItemPriceAndIsEcoResponse> getItemPriceAndIsEcoByIds(@Param("ids") List<Long> ids);
    List<Item> getEventItems();
}
