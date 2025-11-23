package com.apartmentbuilding.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class Apartment extends Room {

    private String ownerName;

    private String apartmentNumber;
}