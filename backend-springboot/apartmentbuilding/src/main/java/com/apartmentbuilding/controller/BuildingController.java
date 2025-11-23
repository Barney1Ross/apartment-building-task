package com.apartmentbuilding.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apartmentbuilding.dto.BuildingRequestDTO;
import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;
import com.apartmentbuilding.service.BuildingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class BuildingController {

    private final BuildingService buildingService;

    // Initialize new building with default rooms
    @PostMapping("/initialize")
    public Building initialize() {
        return buildingService.initializeDefaultBuilding();
    }

    @PostMapping("/initialize-sample")
    public ResponseEntity<List<Building>> initializeSample(@RequestBody List<BuildingRequestDTO> requests) {
        List<Building> buildings = buildingService.initializeSampleBuildings(requests);
        return ResponseEntity.ok(buildings);
    }
    
    // Get all buildings
    @GetMapping
    public List<Building> getAll() {
        return buildingService.getAllBuildings();
    }

    // Get single building
    @GetMapping("/{buildingId}")
    public Building getOne(@PathVariable Long buildingId) {
        return buildingService.getBuilding(buildingId);
    }

    // Update requested temperature
    @PutMapping("/{buildingId}/temperature")
    public Building changeTemperature(
            @PathVariable Long buildingId,
            @RequestParam double requestedTemperature) {
        return buildingService.updateRequestedTemperature(buildingId, requestedTemperature);
    }

    // Add an apartment to building
    @PostMapping("/{buildingId}/apartments")
    public Apartment createApartment(
            @PathVariable Long buildingId,
            @RequestBody Apartment apartment) {
        return buildingService.addApartment(buildingId, apartment);
    }

    // Add a common room to building
    @PostMapping("/{buildingId}/common-rooms")
    public CommonRoom createCommonRoom(
            @PathVariable Long buildingId,
            @RequestBody CommonRoom room) {
        return buildingService.addCommonRoom(buildingId, room);
    }

    // Get all apartments for a building
    @GetMapping("/{buildingId}/apartments")
    public List<Apartment> getApartments(@PathVariable Long buildingId) {
        return buildingService.getApartments(buildingId);
    }

    // Get all common rooms for a building
    @GetMapping("/{buildingId}/common-rooms")
    public List<CommonRoom> getCommonRooms(@PathVariable Long buildingId) {
        return buildingService.getCommonRooms(buildingId);
    }

    // Remove apartment from building
    @DeleteMapping("/{buildingId}/apartments/{apartmentId}")
    public Building removeApartment(
            @PathVariable Long buildingId,
            @PathVariable Long apartmentId) {
        return buildingService.removeApartment(buildingId, apartmentId);
    }

    // Remove common room from building
    @DeleteMapping("/{buildingId}/common-rooms/{roomId}")
    public Building removeCommonRoom(
            @PathVariable Long buildingId,
            @PathVariable Long roomId) {
        return buildingService.removeCommonRoom(buildingId, roomId);
    }
}
