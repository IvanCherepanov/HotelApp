package com.example.demo.services;

import com.example.demo.model.entity.Card;
import com.example.demo.model.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ICardService extends IAbstractService<Card>{
    List<Object[]> getListByParam(LocalDate inputDate,
                                         LocalDate outputDate,
                                         int capacity);
}
