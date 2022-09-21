package com.example.demo.controllers;

import com.example.demo.model.entity.Room;
import com.example.demo.services.IRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/room")
public class RoomController extends AbstractController<Room, IRoomService> {
    protected RoomController(IRoomService service) {
        super(service);
    }
}
