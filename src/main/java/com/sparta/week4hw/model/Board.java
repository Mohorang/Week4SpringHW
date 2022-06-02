package com.sparta.week4hw.model;

import com.sparta.week4hw.dto.CommentRequestDto;
import com.sparta.week4hw.dto.SignupRequestDto;
import com.sparta.week4hw.dto.boardRequestDto;
import com.sparta.week4hw.repository.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;
    //유저이름
    //uniqe 가 true일때 중복을 허용하지 않는다.
    @Column(nullable = false , unique = true)
    private String username;
    //작성 내용
    @Column(nullable = false)
    private String contents;

    //패스워드드
    @Column(nullable = false)
    private String password;

    public Board(boardRequestDto boardRepositoryDto){
        this.username = boardRepositoryDto.getUsername();
        this.contents = boardRepositoryDto.getContents();
        this.password = boardRepositoryDto.getPassword();
        this.title = boardRepositoryDto.getTitle();
    }
    public Board(String username , String password){
        this.username = username;
        this.password = password;
    }
    public void update(boardRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}