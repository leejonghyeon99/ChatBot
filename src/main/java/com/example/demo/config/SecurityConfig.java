package com.example.demo.config;

import com.example.demo.dto.oauth.Role;
import com.example.demo.service.oauth.CustomOAtuh2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomOAtuh2UserService customOAtuh2UserService;

    @Autowired
    public SecurityConfig(CustomOAtuh2UserService customOAtuh2UserService) {
        this.customOAtuh2UserService = customOAtuh2UserService;
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/resources/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("USER","ADMIN")
                    .anyRequest().permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("SESSION")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                .oauth2Login()
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error=true")
                    .userInfoEndpoint()
                    .userService(customOAtuh2UserService);
        return http.build();
    }

}
