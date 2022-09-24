package com.example.demo.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Setter
@Getter
@NoArgsConstructor
public class Room{
    @Id
    @Column(name="id_room")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price_room")
    private int cost;

    @Column(name = "description_room")
    private String description;

    @Column(name = "room_capacity")
    private int capacity;

    @Column(name = "photo_URL")
    private String photoURL;
}
