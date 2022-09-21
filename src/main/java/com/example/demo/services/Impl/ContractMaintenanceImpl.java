package com.example.demo.services.Impl;

import com.example.demo.model.dao.IContractMaintenanceRepository;
import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.services.IContractMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContractMaintenanceImpl extends AbstractServiceImpl<ContractMaintenance, IContractMaintenanceRepository>
        implements IContractMaintenanceService {

    @Autowired
    protected ContractMaintenanceImpl(IContractMaintenanceRepository defaultDao) {
        super(defaultDao);
    }
}
