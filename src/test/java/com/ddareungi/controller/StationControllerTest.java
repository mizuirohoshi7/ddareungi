package com.ddareungi.controller;

import com.ddareungi.config.auth.SessionUser;
import com.ddareungi.config.auth.TestSecurityConfig;
import com.ddareungi.domain.User;
import com.ddareungi.dto.review.ReviewResponseDto;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.service.ReviewService;
import com.ddareungi.service.StationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @MockBean
    ReviewService reviewService;

    @Test
    void 주소_검색_리스트_조회_성공() throws Exception {
        String address = "테스트 주소";
        Page<StationResponseDto> stations = new PageImpl<>(new ArrayList<>());
        given(stationService.search(any(), any(Pageable.class))).willReturn(stations);

        mvc.perform(get("/stations?address=" + address))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("stations"))
                .andExpect(model().attributeExists("address"))
                .andExpect(model().attributeExists("cur"))
                .andExpect(model().attributeExists("start"))
                .andExpect(model().attributeExists("last"))
                .andExpect(view().name("station/searchList"));
    }

    @Test
    void 로그인전_station_id_검색_성공() throws Exception {
        Long id = 1L;
        List<ReviewResponseDto> reviews = new ArrayList<>();
        given(stationService.findById(anyLong())).willReturn(new StationResponseDto());
        given(reviewService.findAllByStationId(id)).willReturn(reviews);

        mvc.perform(get("/stations/" + id + "?page=3&address=서울"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(model().attributeExists("station"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(model().attributeExists("page"))
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("station/detail"));
    }

    @Test
    void 로그인후_station_id_검색_성공() throws Exception {
        Long id = 1L;
        List<ReviewResponseDto> reviews = new ArrayList<>();
        given(stationService.findById(anyLong())).willReturn(new StationResponseDto());
        given(reviewService.findAllByStationId(id)).willReturn(reviews);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(User.createUser("test", "test", "test")));

        mvc.perform(get("/stations/" + id + "?page=3&address=서울")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("station"))
                .andExpect(model().attributeExists("reviews"))
                .andExpect(model().attributeExists("page"))
                .andExpect(model().attributeExists("address"))
                .andExpect(view().name("station/detail"));
    }

}