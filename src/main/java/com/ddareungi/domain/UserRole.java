package com.ddareungi.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String description;

}
