package com.example.demo.controllers;

import com.example.demo.model.entity.Food;
import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.IMaintenanceService;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/maintenance")
public class MaintenanceController extends AbstractController<Maintenance, IMaintenanceService> {
    @Autowired
    protected MaintenanceController(IMaintenanceService service) {
        super(service);
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("maintenances", service.getAll());
        System.out.println(123);
        return "admin/admin-maintenance.html";
    }

    @GetMapping("/new")
    public String createMaintenance(Model model) {
        // create student object to hold student form data
        Maintenance maintenance = new Maintenance();
        model.addAttribute("maintenance", maintenance);
        return "admin/admin-maintenance-add.html";
    }

    @PostMapping("/create")
    public String saveMaintenanceType(@ModelAttribute("maintenance") Maintenance maintenance) {
        service.create(maintenance);
        return "redirect:/maintenance/list";
    }

    @GetMapping("/edit/{id}")
    public String editMaintenanceType(@PathVariable Long id, Model model) {
        model.addAttribute("maintenance", service.findById(id));
        return "admin/admin-maintenance-edit.html";
    }

    @PostMapping("/{id}")
    public String updateMaintenanecType(@PathVariable Long id,
                                 @ModelAttribute("maintenance") Maintenance maintenance,
                                 Model model) {

        // get pet from database by id
        Maintenance existingMaintenance = service.findById(id);
        existingMaintenance.setId(id);
        existingMaintenance.setCost(maintenance.getCost());
        existingMaintenance.setDescription(maintenance.getDescription());

        // save updated pet object
        service.update(id, existingMaintenance);
        return "redirect:/maintenance/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteFood(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/maintenance/list";
    }
}
