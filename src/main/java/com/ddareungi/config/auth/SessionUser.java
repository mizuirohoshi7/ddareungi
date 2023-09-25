package com.ddareungi.config.auth;

import com.ddareungi.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        picture = user.getPicture();
    }

}
