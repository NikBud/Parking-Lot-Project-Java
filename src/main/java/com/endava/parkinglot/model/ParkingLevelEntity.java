package com.endava.parkinglot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking_level")
public class ParkingLevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private char floor;

    @Column(nullable = false)
    private String number_of_spaces;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLotEntity parkingLot;

    @OneToMany(mappedBy = "parkingLevelId", cascade = CascadeType.PERSIST)
    private List<ParkingSpaceEntity> parkingSpaces;
}
