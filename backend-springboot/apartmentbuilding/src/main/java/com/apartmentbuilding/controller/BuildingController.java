package com.apartmentbuilding.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;
import com.apartmentbuilding.service.BuildingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/building")
@RequiredArgsConstructor
@CrossOrigin
public class BuildingController {

    private final BuildingService buildingService;

    @PostMapping("/initialize")
    public Building init() {
        return buildingService.initializeDefaultBuilding();
    }

    @GetMapping("/{id}")
    public Building getBuilding(@PathVariable Long id) {
        return buildingService.getBuilding(id);
    }

    @PutMapping("/temperatur/{id}")
    public Building updateTemp(
            @PathVariable Long id,
            @RequestParam double requestedTemperature
    ) {
        return buildingService.updateRequestedTemperature(id, requestedTemperature);
    }

    @PostMapping("/add/apartment/{id}")
    public Apartment addApartment(
            @PathVariable Long id,
            @RequestBody Apartment apartment
    ) {
        return buildingService.addApartment(id, apartment);
    }

    @PostMapping("/add/common-room/{id}")
    public CommonRoom addCommonRoom(
            @PathVariable Long id,
            @RequestBody CommonRoom room
    ) {
        return buildingService.addCommonRoom(id, room);
    }
}