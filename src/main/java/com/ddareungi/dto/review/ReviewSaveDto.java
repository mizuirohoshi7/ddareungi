package com.ddareungi.dto.review;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewSaveDto {

    @NotNull
    private Long stationId;

    @NotNull
    private String content;

    public ReviewSaveDto(Long stationId, String content) {
        this.stationId = stationId;
        this.content = content;
    }

}
