package com.example.demo.controllers;

import com.example.demo.model.entity.Cleaning;
import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.ICleaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/cleaning")
public class CleaningController extends AbstractController<Cleaning, ICleaningService>{

    @Autowired
    protected CleaningController(ICleaningService service) {
        super(service);
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("cleanings", service.getAll());
        System.out.println(123);
        return "admin/admin-cleaning.html";
    }

    @GetMapping("/new")
    public String createCleaning(Model model) {
        // create student object to hold student form data
        Cleaning cleaning = new Cleaning();
        model.addAttribute("cleaning", cleaning);
        return "admin/admin-cleaning-add.html";
    }

    @PostMapping("/create")
    public String saveCleaningType(@ModelAttribute("cleaning") Cleaning cleaning) {
        service.create(cleaning);
        return "redirect:/cleaning/list";
    }

    @GetMapping("/edit/{id}")
    public String editCleaningType(@PathVariable Long id, Model model) {
        model.addAttribute("cleaning", service.findById(id));
        return "admin/admin-cleaning-edit.html";
    }

    @PostMapping("/{id}")
    public String updateCleaningType(@PathVariable Long id,
                                        @ModelAttribute("cleaning") Cleaning cleaning,
                                        Model model) {

        // get pet from database by id
        Cleaning existingCleaning = service.findById(id);
        existingCleaning.setId(id);
        existingCleaning.setCost(cleaning.getCost());
        existingCleaning.setDescription(cleaning.getDescription());
        existingCleaning.setTime(cleaning.getTime());

        // save updated pet object
        service.update(id, existingCleaning);
        return "redirect:/cleaning/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteCleaning(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/cleaning/list";
    }
}
