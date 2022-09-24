package com.example.demo.services;

import com.example.demo.model.entity.ContractMaintenance;

import java.time.LocalDateTime;

public interface IContractMaintenanceService extends IAbstractService<ContractMaintenance> {
    void create(long idClient, long idMaintenance, LocalDateTime date);
}
