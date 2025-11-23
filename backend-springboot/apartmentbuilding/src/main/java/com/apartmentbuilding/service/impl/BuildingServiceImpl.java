package com.apartmentbuilding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apartmentbuilding.dto.BuildingRequestDTO;
import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;
import com.apartmentbuilding.repository.ApartmentRepository;
import com.apartmentbuilding.repository.BuildingRepository;
import com.apartmentbuilding.repository.CommonRoomRepository;
import com.apartmentbuilding.service.BuildingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepo;
    private final ApartmentRepository apartmentRepo;
    private final CommonRoomRepository commonRoomRepo;

    @Override
    public Building getBuilding(Long id) {
        return buildingRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingRepo.findAll();
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

        // save rooms first (IDs required)
        apartmentRepo.save(a101);
        apartmentRepo.save(a102);
        commonRoomRepo.save(gym);
        commonRoomRepo.save(library);

        building.getApartments().add(a101);
        building.getApartments().add(a102);
        building.getCommonRooms().add(gym);
        building.getCommonRooms().add(library);

        updateRoomHeaterCoolerStatus(building);

        return buildingRepo.save(building);
    }


    @Override
    @Transactional
    public List<Building> initializeSampleBuildings(List<BuildingRequestDTO> requests) {
        List<Building> savedBuildings = new ArrayList<>();

        for (BuildingRequestDTO req : requests) {
            Building building = new Building();
            building.setRequestedTemperature(req.getRequestedTemperature());

            // Apartments
            for (BuildingRequestDTO.ApartmentRequest aptReq : req.getApartments()) {
                Apartment apt = new Apartment();
                apt.setOwnerName(aptReq.getOwnerName());
                apt.setApartmentNumber(aptReq.getApartmentNumber());
                apt.setCurrentTemperature(aptReq.getCurrentTemperature());
                apartmentRepo.save(apt);
                building.getApartments().add(apt);
            }

            // Common rooms
            for (BuildingRequestDTO.CommonRoomRequest crReq : req.getCommonRooms()) {
                CommonRoom cr = new CommonRoom();
                cr.setType(CommonRoom.CommonRoomType.valueOf(crReq.getType()));
                cr.setCurrentTemperature(crReq.getCurrentTemperature());
                commonRoomRepo.save(cr);
                building.getCommonRooms().add(cr);
            }

            updateRoomHeaterCoolerStatus(building);

            savedBuildings.add(buildingRepo.save(building));
        }

        return savedBuildings;
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

        Apartment saved = apartmentRepo.save(apartment);
        building.getApartments().add(saved);

        updateRoomHeaterCoolerStatus(building);

        buildingRepo.save(building);
        return saved;
    }

    @Override
    public CommonRoom addCommonRoom(Long buildingId, CommonRoom room) {
        Building building = getBuilding(buildingId);

        CommonRoom saved = commonRoomRepo.save(room);
        building.getCommonRooms().add(saved);

        updateRoomHeaterCoolerStatus(building);

        buildingRepo.save(building);
        return saved;
    }

    @Override
    public List<Apartment> getApartments(Long buildingId) {
        return getBuilding(buildingId).getApartments();
    }

    @Override
    public List<CommonRoom> getCommonRooms(Long buildingId) {
        return getBuilding(buildingId).getCommonRooms();
    }

    @Override
    public Building removeApartment(Long buildingId, Long aptId) {
        Building b = getBuilding(buildingId);
        b.getApartments().removeIf(a -> a.getId().equals(aptId));
        return buildingRepo.save(b);
    }

    @Override
    public Building removeCommonRoom(Long buildingId, Long roomId) {
        Building b = getBuilding(buildingId);
        b.getCommonRooms().removeIf(cr -> cr.getId().equals(roomId));
        return buildingRepo.save(b);
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
