package com.ddareungi.service;

import com.ddareungi.domain.Station;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.repository.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;

    public Page<StationResponseDto> search(String address, Pageable pageable) {
        return stationRepository.search(address, pageable)
                .map(StationResponseDto::new);
    }

    public StationResponseDto findById(Long id) {
        Station station = stationRepository.findById(id).orElseThrow();
        return new StationResponseDto(station);
    }

}
