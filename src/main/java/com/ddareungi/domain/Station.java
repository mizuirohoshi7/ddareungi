package com.ddareungi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double stationLat;

    @Column(nullable = false)
    private Double stationLong;

    @Column(nullable = false)
    private String holdNumber;

    @Builder
    private Station(String address, Double stationLat, Double stationLong, String holdNumber) {
        this.address = address;
        this.stationLat = stationLat;
        this.stationLong = stationLong;
        this.holdNumber = holdNumber;
    }

}
