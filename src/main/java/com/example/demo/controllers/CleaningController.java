package com.example.demo.controllers;

import com.example.demo.model.entity.Cleaning;
import com.example.demo.services.ICleaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/cleaning")
public class CleaningController extends AbstractController<Cleaning, ICleaningService>{

    @Autowired
    protected CleaningController(ICleaningService service) {
        super(service);
    }
}
