package com.sparta.week4hw.service;

import com.sparta.week4hw.dto.LoginRequestDto;
import com.sparta.week4hw.dto.SignupRequestDto;
import com.sparta.week4hw.model.UserInfo;
import com.sparta.week4hw.repository.UserInfoRepository;
import com.sparta.week4hw.repository.boardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final boardRepository boardrepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    //유저 닉네임 중복확인하기
    //닉네임 조건 비밀번호 조건확인
    //비밀번호 확인 과 비밀번호가 일치하는지 확인하기
    public void checkId(String username){
        Optional<UserInfo> found = userInfoRepository.findByUsername(username);
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }
    }
    public void registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String password2 = requestDto.getPassword2();

        //최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)
        boolean usernameCheck = Pattern.matches("^[a-zA-Z0-9]{3,}$", username);
        if(!usernameCheck){
            throw new IllegalArgumentException("아이디 형식을 확인해주세요.");
        }

        //DB에서 아이디 탐색
        Optional<UserInfo> found = userInfoRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        }

        //비밀번호 조건
        boolean passwordCheck = Pattern.matches("^[a-zA-Z0-9]{4,}$", password);
        if (!passwordCheck) {
            throw new IllegalArgumentException("비밀번호는 4자 이상 입력해주세요.");
        }else if (password.contains(username)) {
            throw new IllegalArgumentException("비밀번호에는 아이디가 포함되어서는 안됩니다.");
        }else if (!password.equals(password2)) {
            throw new IllegalArgumentException("같은 비밀번호를 입력해주세요");
        }
        //비밀번호의 암호화
        //암호화된 비밀번호는 로그인시 Security에서 비밀번호를 체크할 때 사용된다.
        //Security에서는 클라이언트가 입력한 비밀번호를 다시 복호화하여 DB의 비밀번호를 체크
        password = passwordEncoder.encode(password);
        password2 = passwordEncoder.encode(password2);

        UserInfo user = new UserInfo(username, password, password2);
        userInfoRepository.save(user);

    }
}