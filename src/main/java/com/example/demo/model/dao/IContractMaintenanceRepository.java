package com.example.demo.model.dao;

import com.example.demo.model.entity.ContractMaintenance;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContractMaintenanceRepository extends IAbstractRepository<ContractMaintenance> {
    @Procedure
    List<Object[]> getMaintenanceListByIdNewVersion(Long ID);
}
