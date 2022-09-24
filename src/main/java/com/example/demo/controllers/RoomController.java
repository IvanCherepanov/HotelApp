package com.example.demo.controllers;

import com.example.demo.model.entity.Food;
import com.example.demo.model.entity.Room;
import com.example.demo.services.IRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/room")
public class RoomController extends AbstractController<Room, IRoomService> {
    protected RoomController(IRoomService service) {
        super(service);
    }

    @GetMapping("/list")
    public String readingAll(Model model) {
        model.addAttribute("rooms", service.getAll());
        System.out.println(123);
        return "admin/admin-room.html";
    }

    @GetMapping("/new")
    public String createRoom(Model model) {
        // create student object to hold student form data
        Room room = new Room();
        model.addAttribute("room", room);
        return "admin/admin-room-add.html";
    }

    @PostMapping("/create")
    public String saveRoom(@ModelAttribute("room") Room room) {
        service.create(room);
        return "redirect:/room/list";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {
        model.addAttribute("room", service.findById(id));
        return "admin/admin-room-edit.html";
    }

    @PostMapping("/{id}")
    public String updateRoom(@PathVariable Long id,
                                 @ModelAttribute("room") Room room,
                                 Model model) {

        // get pet from database by id
        Room existingRoom = service.findById(id);
        existingRoom.setId(id);
        existingRoom.setCapacity(room.getCapacity());
        existingRoom.setCost(room.getCost());
        existingRoom.setPhotoURL(room.getPhotoURL());

        // save updated pet object
        service.update(id, existingRoom);
        return "redirect:/room/list";
    }

    // handler method to handle delete student request

    @GetMapping("del/{id}")
    public String deleteRoom(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/room/list";
    }
}
