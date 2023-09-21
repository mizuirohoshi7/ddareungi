package com.ddareungi.dto.station;

import com.ddareungi.domain.Station;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class StationResponseDto {

    private Long id;
    private String address;
    private String holdNum;

    public StationResponseDto(Station station) {
        id = station.getId();
        address = station.getAddress();
        holdNum = station.getHoldNumber();
    }

}
