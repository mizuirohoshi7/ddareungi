package com.ddareungi.dto.bookmark;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookmarkSaveDto {

    private Long userId;
    private Long stationId;

    public BookmarkSaveDto(Long userId, Long stationId) {
        this.userId = userId;
        this.stationId = stationId;
    }

}
