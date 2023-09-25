package com.ddareungi.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    static User user;

    @BeforeAll
    static void beforeAll() {
        user = User.createUser("테스트 이름", "테스트 이메일", "테스트 프로필");
    }

    @Test
    void 유저_생성_성공() {
        assertThat(user.getId()).isNull();
        assertThat(user.getName()).isNotNull();
        assertThat(user.getEmail()).isNotNull();
        assertThat(user.getPicture()).isNotNull();
        assertThat(user.getRole()).isNotNull();
    }

    @Test
    void 유저_수정_성공() {
        user.update("수정된 이름", "수정된 프로필");

        assertThat(user.getName()).isEqualTo("수정된 이름");
        assertThat(user.getPicture()).isEqualTo("수정된 프로필");
    }

    @Test
    void 유저_roleKey_반환() {
        String roleKey = user.getRoleKey();

        assertThat(roleKey).isNotNull();
    }

}