package com.ddareungi.controller;

import com.ddareungi.config.auth.SessionUser;
import com.ddareungi.config.auth.TestSecurityConfig;
import com.ddareungi.domain.Review;
import com.ddareungi.domain.Station;
import com.ddareungi.domain.User;
import com.ddareungi.dto.review.ReviewResponseDto;
import com.ddareungi.dto.review.ReviewSaveDto;
import com.ddareungi.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestSecurityConfig.class)
@WebMvcTest(ReviewApiController.class)
class ReviewApiControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ReviewService reviewService;

    static User user;
    static Review review;
    static ReviewResponseDto responseDto;

    @BeforeAll
    static void beforeAll() {
        user = User.createUser("테스트 이름", "테스트 이메일", "테스트 프로필");
        Station station = Station.builder()
                .address("테스트 주소")
                .stationLat(0.0)
                .stationLong(0.0)
                .holdNumber("테스트 개수")
                .reviews(new ArrayList<>())
                .build();
        review = Review.createReview("테스트 내용", user, station);
        responseDto = new ReviewResponseDto(review);
    }

    @Test
    void review_저장_성공() throws Exception {
        given(reviewService.save(any(), any(ReviewSaveDto.class))).willReturn(responseDto);
        Map<String, String> saveInput = new HashMap<>();
        saveInput.put("stationId", "5");
        saveInput.put("content", "테스트 내용");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));

        mvc.perform(post("/reviews/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(saveInput))
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    void review_삭제_성공() throws Exception {
        Long reviewId = 5L;
        given(reviewService.delete(any())).willReturn(responseDto);
        Map<String, String> deleteInput = new HashMap<>();
        deleteInput.put("reviewId", String.valueOf(reviewId));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));

        mvc.perform(delete("/reviews/" + reviewId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(deleteInput)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").exists())
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.createdAt").exists());

    }

}