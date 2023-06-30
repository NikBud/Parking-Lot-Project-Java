package com.endava.parkinglot.services;

import com.endava.parkinglot.DTO.ParkingLotCreationDtoRequest;
import com.endava.parkinglot.DTO.ParkingLotCreationDtoResponse;

import java.util.List;

public interface ParkingLotCreationService {
    ParkingLotCreationDtoResponse createParkingLot(ParkingLotCreationDtoRequest parkingLotCreationDtoRequest);
    List<ParkingLotCreationDtoResponse> getAllParkingLot(String name);
    void addUser(Long id, Long userId);

}
