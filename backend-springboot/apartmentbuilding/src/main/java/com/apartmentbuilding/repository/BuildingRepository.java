package com.apartmentbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apartmentbuilding.entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Long> {

}
