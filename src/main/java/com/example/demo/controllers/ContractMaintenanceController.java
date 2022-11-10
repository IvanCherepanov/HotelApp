package com.example.demo.controllers;

import com.example.demo.model.entity.Client;
import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.services.IClientService;
import com.example.demo.services.IContractMaintenanceService;
import com.example.demo.services.IMaintenanceService;
import com.example.demo.services.Impl.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/contractMaintenance")
public class ContractMaintenanceController extends AbstractController<ContractMaintenance, IContractMaintenanceService> {
    private IMaintenanceService iMaintenanceService;
    private IClientService iUserService;
    private  IContractMaintenanceService iContractMaintenanceService;
    @Autowired
    protected ContractMaintenanceController(IContractMaintenanceService service,
                                            IMaintenanceService iMaintenanceService,
                                           IClientService iUserService,
                                            IContractMaintenanceService iContractMaintenanceService) {
        super(service);
        this.iMaintenanceService = iMaintenanceService;
        this.iUserService = iUserService;
        this.iContractMaintenanceService = iContractMaintenanceService;
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("contractMaintenances", service.getAll());
        model.addAttribute("maintenances", iMaintenanceService.getAll());
        System.out.println(123);
        return "admin/admin-contractMaintenance.html";
    }

    @GetMapping("/myList")
    public String readingAllByClient(Authentication authentication,
                                     Model model) {
        //model.addAttribute("contractMaintenances", service.getAll());
        model.addAttribute("maintenances", iMaintenanceService.getAll());
        Long userId = ((Client) (((ClientServiceImpl) iUserService).loadUserByUsername(authentication.getName()))).getId();
        List<Object[]> temp = iContractMaintenanceService.getListById(userId);
        model.addAttribute("list", iContractMaintenanceService.getListById(userId));
        model.addAttribute("currentId", userId);
        model.addAttribute("totalPrice", iContractMaintenanceService.getTotalCostMaintenance(temp));
        System.out.println(123);
        return "contractMaintenance.html";
    }

    @GetMapping("del/{id}")
    public String deleteCleaning(@PathVariable Long id) {
        iContractMaintenanceService.delete(id);
        return "redirect:/contractMaintenance/list";
    }
}
