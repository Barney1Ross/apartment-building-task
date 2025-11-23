package com.apartmentbuilding.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.apartmentbuilding.entity.Room;
import com.apartmentbuilding.repository.BuildingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TemperatureScheduler {

    private final BuildingRepository buildingRepo;

    private final double CLOSE_ENOUGH = 0.5;

    private double randomDelta() {
        return 0.1 + Math.random() * 0.3;
    }

    @Scheduled(fixedRate = 2000)
    @Transactional
    public void updateTemperatures() {
        buildingRepo.findAll().forEach(building -> {
            building.getApartments().forEach(r -> updateRoom(r, building.getRequestedTemperature()));
            building.getCommonRooms().forEach(r -> updateRoom(r, building.getRequestedTemperature()));

            buildingRepo.save(building); // optional if using @Transactional
        });
    }

    private void updateRoom(Room r, double requested) {
        double curr = r.getCurrentTemperature();
        if (Math.abs(curr - requested) <= CLOSE_ENOUGH) {
            r.setHeatingEnabled(false);
            r.setCoolingEnabled(false);
        } else if (curr < requested) {
            r.setHeatingEnabled(true);
            r.setCoolingEnabled(false);
            r.setCurrentTemperature(curr + randomDelta());
        } else {
            r.setHeatingEnabled(false);
            r.setCoolingEnabled(true);
            r.setCurrentTemperature(curr - randomDelta());
        }
    }
}
