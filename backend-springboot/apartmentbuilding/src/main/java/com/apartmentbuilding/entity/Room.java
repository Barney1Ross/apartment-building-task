package com.apartmentbuilding.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double currentTemperature;

    private boolean heatingEnabled;

    private boolean coolingEnabled;

    @PrePersist
    public void initializeTemperature() {
        if (currentTemperature == 0) {
            currentTemperature = 10 + Math.random() * 30;
        }
    }
}