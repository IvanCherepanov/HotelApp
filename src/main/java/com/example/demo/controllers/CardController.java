package com.example.demo.controllers;

import com.example.demo.model.entity.Card;
import com.example.demo.services.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/card")
public class CardController extends AbstractController<Card, ICardService>{

    @Autowired
    protected CardController(ICardService service) {
        super(service);
    }
}
