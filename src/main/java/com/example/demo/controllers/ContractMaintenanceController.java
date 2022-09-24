package com.example.demo.controllers;

import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.services.IContractMaintenanceService;
import com.example.demo.services.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/contractMaintenance")
public class ContractMaintenanceController extends AbstractController<ContractMaintenance, IContractMaintenanceService> {
    private IMaintenanceService iMaintenanceService;
    @Autowired
    protected ContractMaintenanceController(IContractMaintenanceService service,
                                            IMaintenanceService iMaintenanceService) {
        super(service);
        this.iMaintenanceService = iMaintenanceService;
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("contractMaintenances", service.getAll());
        model.addAttribute("maintenances", iMaintenanceService.getAll());
        System.out.println(123);
        return "admin/admin-contractMaintenance.html";
    }
}
