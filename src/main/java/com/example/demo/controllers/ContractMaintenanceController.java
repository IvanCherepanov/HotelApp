package com.example.demo.controllers;

import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.services.IContractMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/contractMaintenance")
public class ContractMaintenanceController extends AbstractController<ContractMaintenance, IContractMaintenanceService> {
    @Autowired
    protected ContractMaintenanceController(IContractMaintenanceService service) {
        super(service);
    }
}
