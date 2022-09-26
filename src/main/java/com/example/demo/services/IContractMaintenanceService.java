package com.example.demo.services;

import com.example.demo.model.entity.ContractMaintenance;

import java.time.LocalDateTime;
import java.util.List;

public interface IContractMaintenanceService extends IAbstractService<ContractMaintenance> {
    void create(long idClient, long idMaintenance, LocalDateTime date);
    List<Object[]>  getListById(Long id);
    int getTotalCostMaintenance(List<Object[]> temp);
}
