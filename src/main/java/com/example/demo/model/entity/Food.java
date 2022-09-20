package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "food")
@Setter
@Getter
@NoArgsConstructor
public class Food{
    @Id
    @Column(name="id_food")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_food")
    private String description;

    @Column(name = "cost_food")
    private int cost;

    @Column(name = "time_food")
    private LocalTime time;
}
