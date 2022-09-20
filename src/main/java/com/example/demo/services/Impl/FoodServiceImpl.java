package com.example.demo.services.Impl;

import com.example.demo.model.DAO.IFoodRepository;
import com.example.demo.model.entity.Food;
import com.example.demo.services.IFoodService;

public class FoodServiceImpl extends AbstractServiceImpl<Food, IFoodRepository> implements IFoodService {
    protected FoodServiceImpl(IFoodRepository defaultDao) {
        super(defaultDao);
    }
}
