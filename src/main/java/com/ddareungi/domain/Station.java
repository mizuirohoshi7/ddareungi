package com.ddareungi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private Long id;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double stationLat;

    @Column(nullable = false)
    private Double stationLong;

    @Column(nullable = false)
    private String holdNumber;

    @Builder
    private Station(List<Review> reviews, String address, Double stationLat, Double stationLong, String holdNumber) {
        this.reviews = reviews;
        this.address = address;
        this.stationLat = stationLat;
        this.stationLong = stationLong;
        this.holdNumber = holdNumber;
    }
}
