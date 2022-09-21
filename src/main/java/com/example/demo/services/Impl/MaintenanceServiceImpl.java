package com.example.demo.services.Impl;

import com.example.demo.model.dao.IMaintenanceRepository;
import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaintenanceServiceImpl extends AbstractServiceImpl<Maintenance, IMaintenanceRepository> implements IMaintenanceService {

    @Autowired
    protected MaintenanceServiceImpl(IMaintenanceRepository defaultDao) {
        super(defaultDao);
    }
}
