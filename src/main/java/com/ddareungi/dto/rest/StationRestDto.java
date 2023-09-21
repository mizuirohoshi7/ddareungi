package com.ddareungi.dto.rest;

import com.ddareungi.domain.Station;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StationRestDto {

    private String STA_ADD1;
    private String STA_ADD2;
    private Double STA_LAT;
    private Double STA_LONG;
    private String HOLD_NUM;

    @Builder
    private StationRestDto(String STA_ADD1, String STA_ADD2, Double STA_LAT, Double STA_LONG, String HOLD_NUM) {
        this.STA_ADD1 = STA_ADD1;
        this.STA_ADD2 = STA_ADD2;
        this.STA_LAT = STA_LAT;
        this.STA_LONG = STA_LONG;
        this.HOLD_NUM = HOLD_NUM;
    }

    public Station toEntity() {
        return Station.builder()
                .address(STA_ADD1 + " " + STA_ADD2)
                .stationLat(STA_LAT)
                .stationLong(STA_LONG)
                .holdNumber(HOLD_NUM)
                .build();
    }

}
