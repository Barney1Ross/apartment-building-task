package com.apartmentbuilding.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double requestedTemperature = 20.0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Apartment> apartments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CommonRoom> commonRooms = new ArrayList<>();
}
