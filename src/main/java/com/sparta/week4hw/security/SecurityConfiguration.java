package com.sparta.week4hw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //패스워드의 암호화
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    //h2 console사용할 수 있게
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

    //시큐리티 에서 접근권한 설정 같은거
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 회원 관리 처리 API (POST /user/**) 에 대해 CSRF 무시
        http.csrf()
                .ignoringAntMatchers("/user/**")
                //POST요청을 문제없이 처리하기위한 코드
                .disable();
        http
                .authorizeHttpRequests((authz) -> {
                            try {
                                authz
                                        //로그인 없이 접근 가능한 API목록
                                        //회원가입 , 로그인 , 게시글 목록 조회 , 게시글 조회 , 댓글 목록 조회
                                        // user --> 회원가입 , 로그인 포함
                                        .antMatchers("/user/**").permitAll()
                                        //게시글 조회
                                        .antMatchers("/post/see").authenticated()
                                        .antMatchers("/post/see/{id}").authenticated()
                                        // image 폴더를 login 없이 허용
                                        .antMatchers("/images/**").permitAll()
                                        .antMatchers("/css/**").permitAll()
                                        //게시글 목록 조회

                                        // css 폴더를 login 없이 허용

                                        .anyRequest().authenticated()
                                        .and()
                                        .formLogin()
                                        .loginPage("/user/login")
                                        .loginProcessingUrl("/user/login")
                                        .defaultSuccessUrl("/")
                                        .failureUrl("/user/login?error").permitAll()
                                        .and()
                                        .logout()
                                        .logoutUrl("/user/logout").permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}
