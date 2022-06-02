package com.sparta.week4hw.controller;

import com.sparta.week4hw.dto.CommentRequestDto;
import com.sparta.week4hw.model.Board;
import com.sparta.week4hw.model.Comment;
import com.sparta.week4hw.repository.boardRepository;
import com.sparta.week4hw.repository.commentRepository;
import com.sparta.week4hw.security.UserDetailsImpl;
import com.sparta.week4hw.service.boardService;
import com.sparta.week4hw.service.commentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class commentController {

    private final boardRepository boardRepository;
    private final commentRepository commentRepository;
    private final commentService commentservice;
    //로그인 토큰을 전달하지 않아도 댓글 조회는 가능하게
    //

    //id 는 게시글번호
    @GetMapping("/comment/{id}")
    public List<Comment> getComment(@PathVariable Long id){
        return commentRepository.findAllByPostId(id);
    }

    //댓글달기 API
    //일단은 토큰신경쓰지말고 달아보기
    //여기서 id는 게시글 번호
    @PostMapping("/comment/{id}")
    public String addComment(@PathVariable Long id , @RequestBody CommentRequestDto requestDto,
                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        //댓글작성 서비스
        commentservice.addComment(id,requestDto,userDetails.getUsername());
        //requestDto 댓글내용
        return "완료";
    }

    //여기의 id는 댓글번호
    @PutMapping("/comment/modify/{id}")
    public Long updateComment(@PathVariable Long id ,@RequestBody CommentRequestDto requestDto){
        commentservice.updateComment(id,requestDto);

        return id;
    }

    //id는 댓글번호
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        commentRepository.deleteById(id);
        return "test";
    }
}
