package com.ddareungi.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookmarkTest {

    @Test
    void 즐겨찾기_생성_성공() {
        User user = User.createUser("테스트 이름", "테스트 이메일", "테스트 프로필");
        Station station = Station.builder().build();
        Bookmark bookmark = new Bookmark(user, station);

        assertThat(bookmark.getUser()).isNotNull();
        assertThat(bookmark.getStation()).isNotNull();
    }

}