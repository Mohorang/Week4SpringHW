package com.sparta.week4hw.dto;

import com.sparta.week4hw.service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


//회원가입에 필요한 데이터를 담을 Dto
//닉네임,비밀번호,비밀번호확인인@Getter
@Getter
//여기에 Setter가 없어서 에러가 발생햇었다. 발생한 에러는 500에러
//에러가 발생한 부분은 회원가입할때 @ResponseBody어노테이션 없이
//진행을 하여 클라이언트로부터 들어온 값이 Dto에 셋팅되지 못햇기때문에 발생한 에러같다.
@Setter
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String password2;
}