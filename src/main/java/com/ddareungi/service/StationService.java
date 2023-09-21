package com.ddareungi.service;

import com.ddareungi.domain.Station;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.repository.station.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;

    public List<StationResponseDto> search(String address, Pageable pageable) {
        Page<Station> page = stationRepository.search(address, pageable);
        List<StationResponseDto> stations = new ArrayList<>();
        for (Station station : page) {
            stations.add(new StationResponseDto(station));
        }
        return stations;
    }

    public StationResponseDto findById(Long id) {
        Station station = stationRepository.findById(id).orElseThrow();
        return new StationResponseDto(station);
    }

}
