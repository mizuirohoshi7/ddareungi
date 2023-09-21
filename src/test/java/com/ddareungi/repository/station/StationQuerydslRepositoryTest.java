package com.ddareungi.repository.station;

import com.ddareungi.domain.Station;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StationQuerydslRepositoryTest {

    @Autowired
    StationRepository stationRepository;

    @Test
    void 주소_검색_리스트_반환_성공() {
        String address = "테스트 주소";
        stationRepository.save(Station.builder()
                .address("테스트 주소")
                .stationLat(0.0)
                .stationLong(0.0)
                .holdNumber("15")
                .build());

        Page<Station> page = stationRepository.search(address, PageRequest.of(0, 10));

        for (Station station : page) {
            assertThat(station.getAddress()).isEqualTo(address);
        }
    }

}