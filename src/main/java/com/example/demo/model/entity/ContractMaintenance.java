package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contract_maintenance")
@Setter
@Getter
@NoArgsConstructor
public class ContractMaintenance {
    @Id
    @Column(name="idcontract_maintenance")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Client_id_client", insertable = false, updatable = false)
    @JsonIgnore
    private Client client;

    @Column(name="Client_id_client")
    private Long clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Maintenance_id_maintenance", insertable = false, updatable = false)
    @JsonIgnore
    private Maintenance maintenance;

    @Column(name="Maintenance_id_maintenance")
    private Long maintenanceId;


    @Column(name="time_contract")
    private LocalDateTime timeContract;

    private String inputTimeString;
}
