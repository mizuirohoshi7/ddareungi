package com.ddareungi.controller;

import com.ddareungi.config.auth.TestSecurityConfig;
import com.ddareungi.domain.Bookmark;
import com.ddareungi.domain.Station;
import com.ddareungi.domain.User;
import com.ddareungi.dto.bookmark.BookmarkResponseDto;
import com.ddareungi.dto.bookmark.BookmarkSaveDto;
import com.ddareungi.service.BookmarkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestSecurityConfig.class)
@WebMvcTest(BookmarkApiController.class)
class BookmarkApiControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BookmarkService bookmarkService;

    static BookmarkResponseDto responseDto;

    static Long userId = 6L;
    static Long stationId = 5L;

    @BeforeAll
    static void beforeAll() {
        Bookmark bookmark = new Bookmark(User.createUser("test", "test", "test"), Station.builder().build());
        responseDto = new BookmarkResponseDto(bookmark);
    }

    @Test
    void 즐겨찾기_저장_성공() throws Exception {
        given(bookmarkService.save(any(BookmarkSaveDto.class))).willReturn(responseDto);
        Map<String, String> saveInput = new HashMap<>();
        saveInput.put("userId", String.valueOf(userId));
        saveInput.put("stationId", String.valueOf(stationId));

        mvc.perform(post("/bookmarks/save")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(saveInput)))
                .andExpect(status().isOk());
    }

    @Test
    void 즐겨찾기_삭제_성공() throws Exception {
        given(bookmarkService.delete(any())).willReturn(responseDto);

        mvc.perform(delete("/bookmarks/3"))
                .andExpect(status().isOk());
    }

}