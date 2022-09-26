package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "card")
@Setter
@Getter
@NoArgsConstructor
public class Card {
    @Id
    @Column(name="idCard")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="input_date")
    private LocalDate inputDate;

    @Column(name="output_date")
    private LocalDate outputDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Client_id_client", insertable = false, updatable = false)
    @JsonIgnore
    private Client client;

    @Column(name="Client_id_client")
    private Long clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id_room", insertable = false, updatable = false)
    @JsonIgnore
    private Room room;

    @Column(name="room_id_room")
    private Long roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Food_id_food", insertable = false, updatable = false)
    @JsonIgnore
    private Food food;

    @Column(name="Food_id_food")
    private Long foodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Cleaning_id_cleaning", insertable = false, updatable = false)
    @JsonIgnore
    private Cleaning cleaning;

    @Column(name="Cleaning_id_cleaning")
    private Long cleaningId;

}
