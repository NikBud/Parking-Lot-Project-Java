package com.endava.parkinglot.controllers;

import com.endava.parkinglot.DTO.ParkingLotCreationDtoRequest;
import com.endava.parkinglot.DTO.ParkingLotCreationDtoResponse;
import com.endava.parkinglot.services.ParkingLotCreationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkingLot")
public class ParkingLotController {

    private final ParkingLotCreationService parkingLotCreationService;

    @Autowired
    public ParkingLotController(ParkingLotCreationService parkingLotCreationService) {
        this.parkingLotCreationService = parkingLotCreationService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ParkingLotCreationDtoResponse>> getAll(
            @RequestParam(required = false) String name) {
        List<ParkingLotCreationDtoResponse> parkingLots
                = parkingLotCreationService.getAllParkingLot(name);
        return new ResponseEntity<>(parkingLots, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ParkingLotCreationDtoResponse> createParkingLot(
            @Valid @RequestBody ParkingLotCreationDtoRequest parkingLotCreationDtoRequest) {
        ParkingLotCreationDtoResponse newParkingLot
                = parkingLotCreationService.createParkingLot(parkingLotCreationDtoRequest);
        return new ResponseEntity<>(newParkingLot, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/addUser")
    public ResponseEntity<HttpStatus> addUserToParkingLot(@PathVariable Long id, @RequestParam(value = "userId") Long userId) {
        parkingLotCreationService.addUser(id, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
