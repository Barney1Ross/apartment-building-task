package com.apartmentbuilding.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apartmentbuilding.entity.Apartment;
import com.apartmentbuilding.service.ApartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/apartments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class ApartmentController {

    private final ApartmentService apartmentService;

    // Get apartment by ID
    @GetMapping("/{id}")
    public Apartment getOne(@PathVariable Long id) {
        return apartmentService.getApartment(id);
    }

    // Update apartment fields
    @PutMapping("/{id}")
    public Apartment update(
            @PathVariable Long id,
            @RequestBody Apartment apt) {
        return apartmentService.updateApartment(id, apt);
    }

    // Delete apartment completely
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
    }
}
