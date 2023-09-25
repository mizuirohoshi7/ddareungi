package com.ddareungi.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserRoleTest {

    @Test
    void UserRole_생성_성공() {
        UserRole role = UserRole.USER;

        assertThat(role.getKey()).isEqualTo("ROLE_USER");
        assertThat(role.getDescription()).isEqualTo("일반 사용자");
    }

}