package com.ddareungi.controller;

import com.ddareungi.config.auth.TestSecurityConfig;
import com.ddareungi.domain.Station;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.service.StationService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestSecurityConfig.class)
@WebMvcTest(StationController.class)
class StationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    StationService stationService;

    @Test
    void 주소_검색_리스트_반환_성공() throws Exception {
        String address = "테스트 주소";
        List<StationResponseDto> stations = new ArrayList<>();
        given(stationService.search(address, PageRequest.of(0, 10))).willReturn(stations);

        mvc.perform(get("/stations?address=" + address))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stations"))
                .andExpect(view().name("searchList"));
    }

    @Test
    void station_id_검색_성공() throws Exception {
        Long id = 1L;
        given(stationService.findById(id)).willReturn(new StationResponseDto(null));

        mvc.perform(get("/station/" + id))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("station"))
                .andExpect(view().name("detail"));
    }

}