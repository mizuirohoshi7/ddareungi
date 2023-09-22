package com.ddareungi.service;

import com.ddareungi.domain.Station;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.repository.station.StationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StationServiceTest {

    @InjectMocks
    StationService stationService;

    @Mock
    StationRepository stationRepository;

    static Page<Station> page;
    static Station station;

    @BeforeAll
    static void beforeAll() {
        station = Station.builder()
                .address("테스트 주소")
                .stationLat(0.0)
                .stationLong(0.0)
                .holdNumber("15")
                .build();

        List<Station> content = new ArrayList<>();
        content.add(station);
        page = new PageImpl<>(content);
    }

    @Test
    void 주소_검색_리스트_반환_성공() {
        String address = "테스트 주소";
        PageRequest pageable = PageRequest.of(0, 10);
        given(stationRepository.search(any(), any(Pageable.class))).willReturn(page);

        Page<StationResponseDto> stationResponseDtos = stationService.search(address, pageable);

        for (StationResponseDto stationResponseDto : stationResponseDtos) {
            assertThat(stationResponseDto.getAddress()).isNotNull();
            assertThat(stationResponseDto.getHoldNum()).isNotNull();
        }
    }

    @Test
    void station_id_검색_성공() {
        Long id = 1L;
        given(stationRepository.findById(anyLong())).willReturn(Optional.of(station));

        StationResponseDto stationResponseDto = stationService.findById(id);

        assertThat(stationResponseDto.getAddress()).isNotEmpty();
        assertThat(stationResponseDto.getHoldNum()).isNotEmpty();
    }

}