package com.example.demo.controllers;

import com.example.demo.model.entity.Food;
import com.example.demo.services.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/food")
public class FoodController extends AbstractController<Food, IFoodService> {

    @Autowired
    protected FoodController(IFoodService service) {
        super(service);
    }
}
