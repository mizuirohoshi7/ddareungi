package com.ddareungi.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    @Builder(access = AccessLevel.PRIVATE)
    private Review(User user, Station station, String content, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.user = user;
        this.station = station;
        this.content = content;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public static Review createReview(String content, User user, Station station) {
        LocalDateTime now = now();
        return Review.builder()
                .user(user)
                .station(station)
                .content(content)
                .createdAt(now)
                .lastModifiedAt(now)
                .build();
    }

    public void update(String content) {
        this.content = content;
        this.lastModifiedAt = now();
    }

}
