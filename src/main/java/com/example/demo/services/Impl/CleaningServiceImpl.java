package com.example.demo.services.Impl;

import com.example.demo.model.dao.ICleaningRepository;
import com.example.demo.model.entity.Cleaning;
import com.example.demo.services.ICleaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CleaningServiceImpl extends AbstractServiceImpl<Cleaning, ICleaningRepository> implements ICleaningService {
    @Autowired
    protected CleaningServiceImpl(ICleaningRepository defaultDao) {
        super(defaultDao);
    }
}
