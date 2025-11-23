package com.apartmentbuilding.dto;

import java.util.List;

import lombok.Data;

@Data
public class BuildingRequestDTO {
    private double requestedTemperature;
    private List<ApartmentRequest> apartments;
    private List<CommonRoomRequest> commonRooms;

    @Data
    public static class ApartmentRequest {
        private String ownerName;
        private String apartmentNumber;
        private double currentTemperature;
    }

    @Data
    public static class CommonRoomRequest {
        private String type; // GYM, LIBRARY, LAUNDRY
        private double currentTemperature;
    }
}
