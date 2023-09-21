package com.ddareungi;

import com.ddareungi.domain.Station;
import com.ddareungi.dto.rest.StationRestDto;
import com.ddareungi.repository.station.StationRepository;
import com.ddareungi.service.rest.StationRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InitStationData {

    private final StationRestService stationRestService;
    private final StationRepository stationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        initStation();
    }

    private void initStation() {
        // 2023-09-21 기준 충전소 데이터의 총 개수는 3053개이다
        for (int i = 0; i <= 3; i++) {
            List<StationRestDto> stationRestDtos = stationRestService.load(i * 1000, i * 1000 + 999);
            for (StationRestDto stationRestDto : stationRestDtos) {
                Station station = stationRestDto.toEntity();
                stationRepository.save(station);
            }
        }
    }

}
