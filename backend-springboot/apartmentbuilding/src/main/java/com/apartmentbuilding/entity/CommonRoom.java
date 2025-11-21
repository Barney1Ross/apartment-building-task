package com.apartmentbuilding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
public class CommonRoom extends Room {

    @Enumerated(EnumType.STRING)
    private CommonRoomType type;

    public enum CommonRoomType {
        GYM, LIBRARY, LAUNDRY
    }
}