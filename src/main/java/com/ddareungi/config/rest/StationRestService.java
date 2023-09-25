package com.ddareungi.config.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StationRestService {

    private final RestTemplate restTemplate;

    @Value("${restKey}")
    private String restKey;

    public List<StationRestDto> load(int start, int end) {
        String url = "http://openapi.seoul.go.kr:8088/" + restKey + "/json/tbCycleStationInfo/" + start + "/" + end;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        String str = response.getBody();

        List<StationRestDto> stationRestDtos = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(str);
            JSONObject stationInfo = (JSONObject) jsonObject.get("stationInfo");
            JSONArray row = (JSONArray) stationInfo.get("row");

            for (int i = 0; i < row.size(); i++) {
                JSONObject station = (JSONObject) row.get(i);

                // 데이터 중 간혹 HOLD_NUM이 없는 데이터가 존재한다
                String HOLD_NUM = "정보 없음";
                if (station.get("HOLD_NUM") != null) {
                    HOLD_NUM = station.get("HOLD_NUM").toString();
                }

                StationRestDto stationRestDto = StationRestDto.builder()
                        .STA_ADD1(station.get("STA_ADD1").toString())
                        .STA_ADD2(station.get("STA_ADD2").toString())
                        .STA_LAT(Double.valueOf(station.get("STA_LAT").toString()))
                        .STA_LONG(Double.valueOf(station.get("STA_LONG").toString()))
                        .HOLD_NUM(HOLD_NUM)
                        .build();
                stationRestDtos.add(stationRestDto);
            }
        } catch (ParseException e) {
            log.error("서울시 open api 데이터를 가져오지 못했습니다(서비스 키 만료 및 URL 확인 등을 해주세요)");
            e.printStackTrace();
        }

        return stationRestDtos;
    }

}
