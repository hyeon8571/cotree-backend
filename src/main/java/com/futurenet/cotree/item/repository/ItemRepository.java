package com.futurenet.cotree.item.repository;

import com.futurenet.cotree.item.domain.Item;
import com.futurenet.cotree.item.domain.ItemDetail;
import com.futurenet.cotree.payment.dto.response.ItemPriceAndIsEcoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemRepository {
    int decreaseStock(@Param("itemId") Long ItemId, @Param("quantity") int quantity);
    List<Item> getItemsByCategory(@Param("categoryId") Long categoryId, @Param("start") int start, @Param("size") int size);
    ItemDetail getItemDetailById(@Param("id") Long id);
    List<Item> getEcoItems(@Param("start") int start, @Param("size") int size);
    ItemPriceAndIsEcoResponse getItemPriceAndIsEcoById(@Param("id") Long id);
    List<Item> searchItems(@Param("keyword")String keyword, @Param("categoryId") Long categoryId, @Param("start") int start, @Param("size") int size, @Param("isGreen") String isGreen);
    long getTotalItemCount();
    long getTotalEcoItemCount();
}
