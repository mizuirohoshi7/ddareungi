package com.ddareungi.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Builder(access = AccessLevel.PRIVATE)
    private User(List<Review> reviews, String name, String email, String picture, UserRole role) {
        this.reviews = reviews;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public String getRoleKey() {
        return role.getKey();
    }

    public static User createUser(String name, String email, String picture) {
        return User.builder()
                .reviews(new ArrayList<>())
                .name(name)
                .email(email)
                .picture(picture)
                .role(UserRole.USER)
                .build();
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

}
