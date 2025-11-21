package com.apartmentbuilding.service.impl;

import org.springframework.stereotype.Service;

import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;
import com.apartmentbuilding.repository.BuildingRepository;
import com.apartmentbuilding.service.BuildingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepo;

    @Override
    public Building getBuilding(Long id) {
        return buildingRepo.findById(id).orElseThrow();
    }

    @Override
    public Building initializeDefaultBuilding() {

        Building building = new Building();
        building.setRequestedTemperature(25.0);

        Apartment a101 = new Apartment();
        a101.setApartmentNumber("101");
        a101.setOwnerName("Owner 101");

        Apartment a102 = new Apartment();
        a102.setApartmentNumber("102");
        a102.setOwnerName("Owner 102");

        CommonRoom gym = new CommonRoom();
        gym.setType(CommonRoom.CommonRoomType.GYM);

        CommonRoom library = new CommonRoom();
        library.setType(CommonRoom.CommonRoomType.LIBRARY);

        building.getApartments().add(a101);
        building.getApartments().add(a102);
        building.getCommonRooms().add(gym);
        building.getCommonRooms().add(library);

        updateRoomHeaterCoolerStatus(building);

        return buildingRepo.save(building);
    }

    @Override
    public Building updateRequestedTemperature(Long id, double newTemp) {
        Building building = getBuilding(id);
        building.setRequestedTemperature(newTemp);

        updateRoomHeaterCoolerStatus(building);

        return buildingRepo.save(building);
    }

    @Override
    public Apartment addApartment(Long buildingId, Apartment apartment) {
        Building building = getBuilding(buildingId);
        building.getApartments().add(apartment);

        updateRoomHeaterCoolerStatus(building);

        buildingRepo.save(building);
        return apartment;
    }

    @Override
    public CommonRoom addCommonRoom(Long buildingId, CommonRoom room) {
        Building building = getBuilding(buildingId);
        building.getCommonRooms().add(room);

        updateRoomHeaterCoolerStatus(building);

        buildingRepo.save(building);
        return room;
    }

    @Override
    public void updateRoomHeaterCoolerStatus(Building building) {
        double requested = building.getRequestedTemperature();

        building.getApartments().forEach(r -> {
            r.setHeatingEnabled(r.getCurrentTemperature() < requested);
            r.setCoolingEnabled(r.getCurrentTemperature() > requested);
        });

        building.getCommonRooms().forEach(r -> {
            r.setHeatingEnabled(r.getCurrentTemperature() < requested);
            r.setCoolingEnabled(r.getCurrentTemperature() > requested);
        });
    }
}
