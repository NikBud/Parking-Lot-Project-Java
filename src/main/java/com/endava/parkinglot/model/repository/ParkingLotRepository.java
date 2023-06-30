package com.endava.parkinglot.model.repository;

import com.endava.parkinglot.model.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, Long> {

    @Query("SELECT lot FROM ParkingLotEntity lot WHERE lot.name LIKE CONCAT('%', :name, '%') OR :name IS NULL")
    List<ParkingLotEntity> search(String name);
}
