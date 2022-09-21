package com.example.demo.services.Impl;

import com.example.demo.model.dao.IRoomRepository;
import com.example.demo.model.entity.Room;
import com.example.demo.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomServiceImpl extends AbstractServiceImpl<Room, IRoomRepository> implements IRoomService {

    @Autowired
    protected RoomServiceImpl(IRoomRepository defaultDao) {
        super(defaultDao);
    }
}
