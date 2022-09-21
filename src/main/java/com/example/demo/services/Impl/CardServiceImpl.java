package com.example.demo.services.Impl;

import com.example.demo.model.dao.ICardRepository;
import com.example.demo.model.entity.Card;
import com.example.demo.services.ICardService;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardServiceImpl extends AbstractServiceImpl<Card, ICardRepository> implements ICardService {
    @Autowired
    protected CardServiceImpl(ICardRepository defaultDao) {
        super(defaultDao);
    }
}
