package com.example.demo.services.Impl;

import com.example.demo.model.dao.IContractMaintenanceRepository;
import com.example.demo.model.entity.ContractMaintenance;
import com.example.demo.services.IContractMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ContractMaintenanceImpl extends AbstractServiceImpl<ContractMaintenance, IContractMaintenanceRepository>
        implements IContractMaintenanceService {

    private IContractMaintenanceRepository iContractMaintenanceRepository;
    @Autowired
    protected ContractMaintenanceImpl(IContractMaintenanceRepository defaultDao) {
        super(defaultDao);
        iContractMaintenanceRepository = defaultDao;
    }

    @Override
    public void create(long idClient, long idMaintenance, LocalDateTime date) {
        ContractMaintenance contractMaintenance = new ContractMaintenance();
        contractMaintenance.setMaintenanceId(idMaintenance);
        contractMaintenance.setClientId(idClient);
        contractMaintenance.setTimeContract(date);
        iContractMaintenanceRepository.save(contractMaintenance);
    }
}
