package com.endava.parkinglot.services.impl;

import com.endava.parkinglot.DTO.ParkingLotCreationDtoRequest;
import com.endava.parkinglot.DTO.ParkingLotCreationDtoResponse;
import com.endava.parkinglot.exceptions.FailedEmailNotificationException;
import com.endava.parkinglot.exceptions.ParkingLotNotFoundException;
import com.endava.parkinglot.exceptions.UserNotFoundException;
import com.endava.parkinglot.mapper.ParkingMapper;
import com.endava.parkinglot.model.ParkingLotEntity;
import com.endava.parkinglot.model.UserEntity;
import com.endava.parkinglot.model.repository.ParkingLotRepository;
import com.endava.parkinglot.model.repository.UserRepository;
import com.endava.parkinglot.services.ParkingLotCreationService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotCreationServiceImpl implements ParkingLotCreationService {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingMapper parkingMapper;
    private final UserRepository userRepository;
    private final EmailNotificationServiceImpl emailNotificationService;
    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationServiceImpl.class);

    public ParkingLotCreationServiceImpl(ParkingLotRepository parkingLotRepository, ParkingMapper parkingMapper, UserRepository userRepository, EmailNotificationServiceImpl emailNotificationService) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingMapper = parkingMapper;
        this.userRepository = userRepository;
        this.emailNotificationService = emailNotificationService;
    }

    @Override
    public List<ParkingLotCreationDtoResponse> getAllParkingLot(String name) {
        return parkingMapper
                    .mapListEntityToListResponseDto(
                            parkingLotRepository.search(name)
                    );
    }

    @Override
    @Transactional
    public void addUser(Long id, Long userId) {
        ParkingLotEntity parkingLot = parkingLotRepository.findById(id).orElseThrow(
                () -> new ParkingLotNotFoundException("Parking Lot with ID " + id + " not found.")
        );

        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with ID " + id + " not found.")
        );

        parkingLot.getUsers().add(user);
        user.getParkingLots().add(parkingLot);

        parkingLotRepository.save(parkingLot);
        userRepository.save(user);

        try {
            emailNotificationService.sendNotificationAboutAddedToParkingLot(user.getEmail(), parkingLot.getName());
        } catch (FailedEmailNotificationException failedEmailNotificationException) {
            logger.warn("Email was not sent, user not added to parking lot: " + user.getEmail());
        }
    }

    @Override
    public ParkingLotCreationDtoResponse createParkingLot(ParkingLotCreationDtoRequest parkingLotCreationDtoRequest) {
        return null;
    }

}
