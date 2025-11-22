package com.apartmentbuilding.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apartmentbuilding.entity.CommonRoom;
import com.apartmentbuilding.service.CommonRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/common-rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class CommonRoomController {

    private final CommonRoomService commonRoomService;

    // Get common room by ID
    @GetMapping("/{id}")
    public CommonRoom getOne(@PathVariable Long id) {
        return commonRoomService.getCommonRoom(id);
    }

    // Update common room (only type)
    @PutMapping("/{id}")
    public CommonRoom update(
            @PathVariable Long id,
            @RequestBody CommonRoom room) {
        return commonRoomService.updateCommonRoom(id, room);
    }

    // Delete common room completely
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        commonRoomService.deleteCommonRoom(id);
    }
}

