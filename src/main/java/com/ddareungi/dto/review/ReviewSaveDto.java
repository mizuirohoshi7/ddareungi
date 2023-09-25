package com.ddareungi.dto.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ReviewSaveDto {

    private Long stationId;
    private String content;

    public ReviewSaveDto(Long stationId, String content) {
        this.stationId = stationId;
        this.content = content;
    }

}
