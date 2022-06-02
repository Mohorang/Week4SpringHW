package com.sparta.week4hw.model;

import com.sparta.week4hw.dto.SignupRequestDto;
import com.sparta.week4hw.repository.Timestamped;
import com.sparta.week4hw.repository.UserInfoRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Setter
public class UserInfo extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    //unique가 true이면 중복을 허용하지 않는다.
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserInfo(String username,String password,String password2){
        if(!username.matches("^[a-zA-Z0-9]{3,}$")){
            throw new IllegalArgumentException("닉네임 조건이 일치하지 않습니다.");
        }

        //패스워드에 아이디가 들어가있고 길이가 4보다 작을때
        if(password.contains(username) || password.length() < 4){
           throw new IllegalArgumentException("비밀번호 형식을 확인해주세요");
        }

        if(!password.equals(password2)){
            throw new IllegalArgumentException("같은 비밀번호를 입력해주세요");
        }
        this.username = username;
        this.password = password;
    }

}
