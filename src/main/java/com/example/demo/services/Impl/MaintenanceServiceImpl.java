package com.example.demo.services.Impl;

import com.example.demo.model.DAO.IMaintenanceRepository;
import com.example.demo.model.entity.Maintenance;
import com.example.demo.services.IMaintenanceService;

public class MaintenanceServiceImpl extends AbstractServiceImpl<Maintenance, IMaintenanceRepository> implements IMaintenanceService {
    protected MaintenanceServiceImpl(IMaintenanceRepository defaultDao) {
        super(defaultDao);
    }
}
