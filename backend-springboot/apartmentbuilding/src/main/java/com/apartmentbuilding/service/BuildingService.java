package com.apartmentbuilding.service;

import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;

public interface BuildingService {

	Building getBuilding(Long id);

	Building initializeDefaultBuilding();

	Building updateRequestedTemperature(Long id, double newTemp);

	Apartment addApartment(Long buildingId, Apartment apartment);

	CommonRoom addCommonRoom(Long buildingId, CommonRoom room);

	void updateRoomHeaterCoolerStatus(Building building);
}
