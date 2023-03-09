package com.example.demo.dto.oauth;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;


    public SessionUser(Member user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
