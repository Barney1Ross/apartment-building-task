package com.apartmentbuilding.service;

import com.apartmentbuilding.entity.Apartment;

public interface ApartmentService {
    Apartment getApartment(Long id);
    Apartment updateApartment(Long id, Apartment updated);
    void deleteApartment(Long id);
}
