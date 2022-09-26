package com.example.demo.services;

import com.example.demo.model.entity.Card;
import com.example.demo.model.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ICardService extends IAbstractService<Card>{
    List<Object[]> getListByParam(LocalDate inputDate,
                                         LocalDate outputDate,
                                         int capacity);
    void create(LocalDate InputDate,
                LocalDate OutputDate,
                Long idClient,
                Long idRoom);
    List<Card> getListByClientId(Long id);

    List<Object[]> getListRoomCostById(Long id);

    Object[] getObjectById(Long id, List<Object[]> fullList);
}
