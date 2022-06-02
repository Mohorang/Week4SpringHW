package com.sparta.week4hw.model;

import com.sparta.week4hw.dto.SignupRequestDto;
import com.sparta.week4hw.repository.UserInfoRepository;
import com.sparta.week4hw.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserInfoTest {

    @Autowired
    private UserInfoRepository userInfo;
    @Autowired
    private UserService userService;


    @BeforeEach
    void setting(){
    }
    //필요한 테스트가 닉네임 3자이상 테스트
    //닉네임 정규식 테스트
    //닉네임 중복 테스트
    //비밀번호 비밀번호 확인 일치테스트

    //닉네임 정규식 테스트1 성공하게끔
    @Test
    @DisplayName("성공하는 닉네임 정규식 테스트1") //
    void usernameTest1(){
        //given 주어진 환경을 이야기한다.
        String username = "testId";
        String password = "testPw";
        String password2 = "testPw";
        //when 이 코드가 실행된다면
        UserInfo userInfo = new UserInfo(username,password,password2);

        //then 결과가 나와야한다.

        assertEquals(username,userInfo.getUsername());
    }
    //닉네임 정규식 테스트1 실패하게끔
    @Test
    @DisplayName("실패하는 닉네임 정규식 테스트2") //
    void usernameTest2(){
        //given 주어진 환경을 이야기한다.
        String username = "id";
        String password = "testPw";
        String password2 = "testPw";
        //when 이 코드가 실행된다면
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    new UserInfo(username,password,password2);
                });
        //then 결과가 나와야한다.
        assertEquals("닉네임 조건이 일치하지 않습니다.",exception.getMessage());
    }
    @Test
    @DisplayName("닉네임 중복 테스트1") //
    @Transactional //테스트 코드에서는 역할이 다르다.
    void usernameTest3(){
        //given 주어진 환경을 이야기한다.
        String username = "testId";
        String password = "testPw";
        String password2 = "testPw";
        UserInfo userInfo1 = new UserInfo(username,password,password2);

        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setUsername(username);
        requestDto.setPassword(password);
        requestDto.setPassword2(password2);

        //when 이 코드가 실행된다면
        userInfo.save(userInfo1);

        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    userService.registerUser(requestDto);
                });

        //then 결과가 나와야한다.
        assertEquals("중복된 닉네임입니다.",exception.getMessage());

        userInfo.delete(userInfo1);
    }
    @Test
    @DisplayName("닉네임 중복 테스트2") //
    void usernameTest4(){
        //given 주어진 환경을 이야기한다.
        String username = "testId2";
        String password = "testPw";
        String password2 = "testPw";
        UserInfo userInfo1 = new UserInfo(username,password,password2);

        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setUsername(username);
        requestDto.setPassword(password);
        requestDto.setPassword2(password2);

        //when 이 코드가 실행된다면
        userInfo.save(userInfo1);
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    userService.registerUser(requestDto);
                });
        //then 결과가 나와야한다.
        assertEquals("중복된 닉네임입니다.",exception.getMessage());

        userInfo.delete(userInfo1);
    }

    //비밀번호 조건 실패테스트
    @Test
    @DisplayName("비밀번호 실패 테스트1") //
    void passwordTest1(){
        //given 주어진 환경을 이야기한다.
        String username = "testId";
        String password = "testIdPw";
        String password2 = "testIdPw";

        //when 이 코드가 실행된다면
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    new UserInfo(username,password,password2);
                });

        //then 결과가 나와야한다.
        assertEquals("비밀번호 형식을 확인해주세요",exception.getMessage());
    }

    //비밀번호 조건 실패테스트 2
    @Test
    @DisplayName("비밀번호 실패 테스트2") //
    void passwordTest2(){
        //given 주어진 환경을 이야기한다.
        String username = "testId";
        String password = "tes";
        String password2 = "tes";

        //when 이 코드가 실행된다면
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    new UserInfo(username,password,password2);
                });

        //then 결과가 나와야한다.
        assertEquals("비밀번호 형식을 확인해주세요",exception.getMessage());
    }

    //비밀번호 조건 실패테스트 2
    @Test
    @DisplayName("비밀번호 ,비밀번호 확인 일치 테스트1") //
    void passwordTest3(){
        //given 주어진 환경을 이야기한다.
        String username = "testId";
        String password = "testPw";
        String password2 = "testPW";

        //when 이 코드가 실행된다면
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    new UserInfo(username,password,password2);
                });

        //then 결과가 나와야한다.
        assertEquals("같은 비밀번호를 입력해주세요",exception.getMessage());
    }

    //비밀번호 조건 실패테스트 2
    @Test
    @DisplayName("비밀번호 ,비밀번호 확인 일치 테스트2") //
    void passwordTest4(){
        //given 주어진 환경을 이야기한다.
        String username = "testId";
        String password = "test111";
        String password2 = "test222";

        //when 이 코드가 실행된다면
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> {
                    new UserInfo(username,password,password2);
                });

        //then 결과가 나와야한다.
        assertEquals("같은 비밀번호를 입력해주세요",exception.getMessage());
    }
}