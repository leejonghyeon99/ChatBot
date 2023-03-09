package com.example.demo.mapper;

import com.example.demo.dto.oauth.Member;

public interface MemberMapper {

    Member findByEmail(String email);
    void save(Member user);
    void update(Member user);
}
