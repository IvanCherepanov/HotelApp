package com.example.demo.services.Impl;

import com.example.demo.model.DAO.ICleaningRepository;
import com.example.demo.model.entity.Cleaning;
import com.example.demo.services.ICleaningService;

public class CleaningServiceImpl extends AbstractServiceImpl<Cleaning, ICleaningRepository> implements ICleaningService {
    protected CleaningServiceImpl(ICleaningRepository defaultDao) {
        super(defaultDao);
    }
}
