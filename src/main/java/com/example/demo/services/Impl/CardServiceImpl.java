package com.example.demo.services.Impl;

import com.example.demo.model.DAO.ICardRepository;
import com.example.demo.model.entity.Card;
import com.example.demo.services.ICardService;

public class CardServiceImpl extends AbstractServiceImpl<Card, ICardRepository> implements ICardService {
    protected CardServiceImpl(ICardRepository defaultDao) {
        super(defaultDao);
    }
}
