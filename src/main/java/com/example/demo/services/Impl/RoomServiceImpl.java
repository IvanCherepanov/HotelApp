package com.example.demo.services.Impl;

import com.example.demo.model.DAO.IRoomRepository;
import com.example.demo.model.entity.Room;
import com.example.demo.services.IRoomService;

public class RoomServiceImpl extends AbstractServiceImpl<Room, IRoomRepository> implements IRoomService {
    protected RoomServiceImpl(IRoomRepository defaultDao) {
        super(defaultDao);
    }
}
