package com.example.demo.services.Impl;

import com.example.demo.model.DAO.IContractMaintenanceRepository;
import com.example.demo.model.entity.Cleaning;
import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.services.IContractMaintenanceService;

public class ContractMaintenanceImpl extends AbstractServiceImpl<ContractMaintenance, IContractMaintenanceRepository>
        implements IContractMaintenanceService {

    protected ContractMaintenanceImpl(IContractMaintenanceRepository defaultDao) {
        super(defaultDao);
    }
}
