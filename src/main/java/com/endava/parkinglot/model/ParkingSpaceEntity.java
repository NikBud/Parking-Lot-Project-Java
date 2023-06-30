package com.endava.parkinglot.model;

import com.endava.parkinglot.enums.SpaceState;
import com.endava.parkinglot.enums.SpaceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking_space")
public class ParkingSpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int number;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("REGULAR")
    private SpaceType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("AVAILABLE")
    private SpaceState state;

    @ManyToOne
    @JoinColumn(name = "parking_level_id")
    private ParkingLevelEntity parkingLevelId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
