package com.endava.parkinglot.model;

import com.endava.parkinglot.enums.WorkingDays;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "working_days")
@Entity
public class WorkingDaysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_of_day")
    @Enumerated(EnumType.STRING)
    private WorkingDays nameOfDay;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLotEntity parkingLot;
}
