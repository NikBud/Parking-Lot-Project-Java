package com.endava.parkinglot.model;

import com.endava.parkinglot.enums.LotState;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking_lot")
public class ParkingLotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "begin_working_hour")
    private Time beginWorkingHour;

    @Column(nullable = false, name = "end_working_hour")
    private Time endWorkingHour;

    @Column(nullable = false)
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.PERSIST)
    private List<WorkingDaysEntity> working_days;

    @Column(nullable = false)
    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<ParkingLevelEntity> levels;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LotState state;

    @ManyToMany(mappedBy = "parkingLots")
    Set<UserEntity> users;
}
