package com.example.demo.service.oauth;

import com.example.demo.dto.oauth.Member;
import com.example.demo.dto.oauth.OAuthAttributes;
import com.example.demo.dto.oauth.SessionUser;
import com.example.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
public class CustomOAtuh2UserService implements OAuth2UserService<OAuth2UserRequest,OAuth2User> {

    private final HttpSession httpSession;
    private final MemberMapper memberMapper;

    @Autowired
    public CustomOAtuh2UserService(HttpSession httpSession, MemberMapper memberMapper) {
        this.httpSession = httpSession;
        this.memberMapper = memberMapper;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,oAuth2User.getAttributes());

        Member user = saveOrUpdate(attributes);
        SessionUser sessionUser = new SessionUser(user);
        httpSession.setAttribute("user",sessionUser);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private Member saveOrUpdate(OAuthAttributes attributes){
        Member user = memberMapper.findByEmail((attributes.getEmail()));
        if (user == null) {
            // 새로운 사용자 추가
            memberMapper.save(attributes.toEntity());
        } else {
            // 기존 사용자 정보 업데이트
            memberMapper.update(attributes.toEntity());
        }
        return user;
    }
}
