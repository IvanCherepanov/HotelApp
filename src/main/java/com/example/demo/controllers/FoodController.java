package com.example.demo.controllers;

import com.example.demo.model.entity.Food;
import com.example.demo.services.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/food")
public class FoodController extends AbstractController<Food, IFoodService> {

    @Autowired
    protected FoodController(IFoodService service) {
        super(service);
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("foods", service.getAll());
        System.out.println(123);
        return "admin/admin-food.html";
    }

    @GetMapping("/new")
    public String createFood(Model model) {
        // create student object to hold student form data
        Food food = new Food();
        model.addAttribute("food", food);
        return "admin/admin-food-add.html";
    }

    @PostMapping("/create")
    public String saveFoodType(@ModelAttribute("food") Food food) {
        service.create(food);
        return "redirect:/food/list";
    }

    @GetMapping("/edit/{id}")
    public String editFoodType(@PathVariable Long id, Model model) {
        model.addAttribute("food", service.findById(id));
        return "admin/admin-food-edit.html";
    }

    @PostMapping("/{id}")
    public String updateFoodType(@PathVariable Long id,
                                 @ModelAttribute("food") Food food,
                                 Model model) {

        // get pet from database by id
        Food existingFood = service.findById(id);
        existingFood.setId(id);
        existingFood.setDescription(food.getDescription());
        existingFood.setCost(food.getCost());
        existingFood.setTime(food.getTime());

        // save updated pet object
        service.update(id, existingFood);
        return "redirect:/food/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteFood(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/food/list";
    }
}
