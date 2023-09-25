package com.ddareungi.dto.station;

import com.ddareungi.domain.Station;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StationResponseDto {

    private Long id;
    private String address;
    private Double stationLat;
    private Double stationLong;
    private String holdNum;

    public StationResponseDto(Station station) {
        id = station.getId();
        address = station.getAddress();
        stationLat = station.getStationLat();
        stationLong = station.getStationLong();
        holdNum = station.getHoldNumber();
    }

}
