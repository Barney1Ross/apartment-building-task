package com.apartmentbuilding.service.impl;

import org.springframework.stereotype.Service;

import com.apartmentbuilding.entity.Building;
import com.apartmentbuilding.entity.CommonRoom;
import com.apartmentbuilding.repository.BuildingRepository;
import com.apartmentbuilding.repository.CommonRoomRepository;
import com.apartmentbuilding.service.CommonRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonRoomServiceImpl implements CommonRoomService {

    private final CommonRoomRepository roomRepo;

    @Override
    public CommonRoom getCommonRoom(Long id) {
        return roomRepo.findById(id).orElseThrow();
    }

    @Override
    public CommonRoom updateCommonRoom(Long id, CommonRoom updated) {
        CommonRoom existing = roomRepo.findById(id).orElseThrow();
        existing.setType(updated.getType());
        return roomRepo.save(existing);
    }

    @Override
    public void deleteCommonRoom(Long id) {
        roomRepo.deleteById(id);
    }
}
