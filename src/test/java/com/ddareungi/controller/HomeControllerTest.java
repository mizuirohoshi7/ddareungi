package com.ddareungi.controller;

import com.ddareungi.config.auth.SessionUser;
import com.ddareungi.config.auth.TestSecurityConfig;
import com.ddareungi.domain.User;
import com.ddareungi.dto.station.StationResponseDto;
import com.ddareungi.service.StationService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(TestSecurityConfig.class)
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    StationService stationService;

    @Test
    void 로그인전_홈_화면_불러오기_성공() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("user"))
                .andExpect(view().name("home"));
    }

    @Test
    void 로그인후_홈_화면_불러오기_성공() throws Exception {
        MockHttpSession session = new MockHttpSession();
        User user = User.createUser("테스트 이름", "테스트 이메일", "테스트 프로필");
        session.setAttribute("user", new SessionUser(user));
        List<StationResponseDto> stations = new ArrayList<>();
        given(stationService.findBookmarked(anyLong())).willReturn(stations);

        mvc.perform(get("/").session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("stations"))
                .andExpect(view().name("home"));
    }

}