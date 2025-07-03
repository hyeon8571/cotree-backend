package com.futurenet.cotree.item.repository;

import com.futurenet.cotree.item.domain.EventItem;
import com.futurenet.cotree.item.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventItemRepository {
    List<Item> getEventItems();
    int saveEventItems(@Param("eventItems") List<EventItem> eventItems);
}
