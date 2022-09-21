package com.example.demo.controllers;

import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/maintenance")
public class MaintenanceController extends AbstractController<Maintenance, IMaintenanceService> {
    @Autowired
    protected MaintenanceController(IMaintenanceService service) {
        super(service);
    }
}
