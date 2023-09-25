package com.ddareungi.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class StationTest {

    @Test
    void station_생성_성공() {
        Station station = Station.builder()
                .address("테스트 주소")
                .stationLat(0.0)
                .stationLong(0.0)
                .holdNumber("테스트 개수")
                .reviews(new ArrayList<>())
                .build();

        assertThat(station.getId()).isNull();
        assertThat(station.getAddress()).isNotNull();
        assertThat(station.getStationLat()).isNotNull();
        assertThat(station.getStationLong()).isNotNull();
        assertThat(station.getReviews()).isNotNull();
    }

}