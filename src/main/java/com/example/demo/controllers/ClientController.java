package com.example.demo.controllers;


import com.example.demo.model.entity.Client;
import com.example.demo.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/client")
public class ClientController extends AbstractController<Client, IClientService>{
    @Autowired
    protected ClientController(IClientService service) {
        super(service);
    }
}
