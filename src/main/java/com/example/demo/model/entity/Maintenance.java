package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "maintenance")
@Setter
@Getter
@NoArgsConstructor
public class Maintenance{
    @Id
    @Column(name="id_maintenance")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="description_maintenance")
    private String description;

    @Column(name="cost_maintenance")
    private int cost;
}
