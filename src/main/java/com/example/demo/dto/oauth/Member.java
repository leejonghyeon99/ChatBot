package com.example.demo.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    private LocalDateTime created;

}
