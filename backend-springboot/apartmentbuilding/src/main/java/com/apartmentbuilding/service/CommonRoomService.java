package com.apartmentbuilding.service;

import com.apartmentbuilding.entity.CommonRoom;

public interface CommonRoomService {
    CommonRoom getCommonRoom(Long id);
    CommonRoom updateCommonRoom(Long id, CommonRoom updated);
    void deleteCommonRoom(Long id);
}
