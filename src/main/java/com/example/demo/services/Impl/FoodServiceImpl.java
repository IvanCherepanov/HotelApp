package com.example.demo.services.Impl;

import com.example.demo.model.dao.IFoodRepository;
import com.example.demo.model.entity.Food;
import com.example.demo.services.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FoodServiceImpl extends AbstractServiceImpl<Food, IFoodRepository> implements IFoodService {

    @Autowired
    protected FoodServiceImpl(IFoodRepository defaultDao) {
        super(defaultDao);
    }
}
