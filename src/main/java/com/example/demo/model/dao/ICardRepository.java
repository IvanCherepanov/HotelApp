package com.example.demo.model.dao;

import com.example.demo.model.entity.Card;
import com.example.demo.model.entity.Room;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICardRepository extends IAbstractRepository<Card>{
    @Procedure
    List<Object[]> getListByCapacityAndRange(LocalDate inputDate,
                                         LocalDate outputDate,
                                         int capacity);
 }
