package com.example.demo.model.dao;

import com.example.demo.model.entity.Food;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends IAbstractRepository<Food> {
    Food getFoodByDescription(String desc);
}
