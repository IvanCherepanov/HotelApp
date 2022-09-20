package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "cleaning")
@Setter
@Getter
@NoArgsConstructor
public class Cleaning {
    @Id
    @Column(name="id_cleaning")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_cleaning")
    private String description;

    @Column(name = "cost_cleaning")
    private int cost;

    @Column(name = "time_cleaning")
    private LocalTime time;
}
