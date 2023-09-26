package com.ddareungi.dto.bookmark;

import com.ddareungi.domain.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkResponseDto {

    private Long id;
    private Long userId;
    private Long stationId;

    public BookmarkResponseDto(Bookmark bookmark) {
        id = bookmark.getId();
        userId = bookmark.getUser().getId();
        stationId = bookmark.getStation().getId();
    }

}
