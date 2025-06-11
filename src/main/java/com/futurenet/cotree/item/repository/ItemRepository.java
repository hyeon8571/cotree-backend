package com.futurenet.cotree.item.repository;

import com.futurenet.cotree.item.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemRepository {
    List<ItemDto> findItemsByCategory(@Param("categoryId") Long categoryId);
}
