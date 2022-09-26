package com.example.demo.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ClientMaintenance {
    private String clientName;

    private int costMaintenance;

    private LocalDateTime time_contract;

    private String description_maintenance;
}
