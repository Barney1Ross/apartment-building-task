package com.apartmentbuilding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apartmentbuilding.entity.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
