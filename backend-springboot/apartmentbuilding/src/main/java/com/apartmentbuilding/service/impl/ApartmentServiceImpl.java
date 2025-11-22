package com.apartmentbuilding.service.impl;

import org.springframework.stereotype.Service;

import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.repository.ApartmentRepository;
import com.apartmentbuilding.service.ApartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepo;

    @Override
    public Apartment getApartment(Long id) {
        return apartmentRepo.findById(id).orElseThrow();
    }

    @Override
    public Apartment updateApartment(Long id, Apartment updated) {
        Apartment existing = apartmentRepo.findById(id).orElseThrow();
        existing.setOwnerName(updated.getOwnerName());
        existing.setApartmentNumber(updated.getApartmentNumber());
        return apartmentRepo.save(existing);
    }

    @Override
    public void deleteApartment(Long id) {
        apartmentRepo.deleteById(id);
    }
}
