package com.apartmentbuilding.service;

import java.util.List;

import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;

public interface BuildingService {

    List<Building> getAllBuildings();

    Building getBuilding(Long id);

    Building initializeDefaultBuilding();

    Building updateRequestedTemperature(Long buildingId, double newTemp);

    Apartment addApartment(Long buildingId, Apartment apartment);

    CommonRoom addCommonRoom(Long buildingId, CommonRoom room);

    Building removeApartment(Long buildingId, Long apartmentId);

    Building removeCommonRoom(Long buildingId, Long roomId);

    List<Apartment> getApartments(Long buildingId);

    List<CommonRoom> getCommonRooms(Long buildingId);

    void updateRoomHeaterCoolerStatus(Building building);
}
