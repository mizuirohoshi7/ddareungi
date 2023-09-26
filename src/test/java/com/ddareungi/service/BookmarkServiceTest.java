package com.ddareungi.service;

import com.ddareungi.domain.Bookmark;
import com.ddareungi.domain.Station;
import com.ddareungi.domain.User;
import com.ddareungi.dto.bookmark.BookmarkResponseDto;
import com.ddareungi.dto.bookmark.BookmarkSaveDto;
import com.ddareungi.repository.bookmark.BookmarkRepository;
import com.ddareungi.repository.station.StationRepository;
import com.ddareungi.repository.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BookmarkServiceTest {

    @InjectMocks
    BookmarkService bookmarkService;

    @Mock
    BookmarkRepository bookmarkRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    StationRepository stationRepository;

    static User user;
    static Station station;
    static Bookmark bookmark;

    static Long userId = 3L;
    static Long stationId = 5L;

    @BeforeAll
    static void beforeAll() {
        user = User.createUser("test", "test", "test");
        station = Station.builder().build();
        bookmark = new Bookmark(user, station);
    }

    @Test
    void 즐겨찾기_저장_성공() {
        BookmarkSaveDto saveDto = new BookmarkSaveDto(userId, stationId);
        given(userRepository.findById(any())).willReturn(Optional.of(user));
        given(stationRepository.findById(any())).willReturn(Optional.of(station));
        given(bookmarkRepository.save(any())).willReturn(bookmark);

        BookmarkResponseDto responseDto = bookmarkService.save(saveDto);

        assertThat(responseDto).isNotNull();
    }

    @Test
    void 즐겨찾기_삭제_성공() {
        Long bookmarkId = 5L;
        given(bookmarkRepository.findById(bookmarkId)).willReturn(Optional.of(bookmark));

        BookmarkResponseDto responseDto = bookmarkService.delete(bookmarkId);

        assertThat(responseDto).isNotNull();
    }

    @Test
    void userId와_stationId로_즐겨찾기_조회_성공() {
        given(bookmarkRepository.findByUserIdAndStationId(any(), any())).willReturn(Optional.of(bookmark));

        BookmarkResponseDto responseDto = bookmarkService.findByUserIdAndStationId(userId, stationId);

        assertThat(responseDto).isNotNull();
    }

}