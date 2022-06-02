package com.sparta.week4hw.service;

import com.sparta.week4hw.dto.CommentRequestDto;
import com.sparta.week4hw.model.Board;
import com.sparta.week4hw.model.Comment;
import com.sparta.week4hw.repository.commentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class commentService {

    private final commentRepository commentRepository;
    public void addComment(Long postId, CommentRequestDto requestDto , String username){
        //댓글을 비워두면 에러메세지 출력
        if(requestDto.getComment().equals("")){
            throw new IllegalArgumentException("댓글 내용을 입력해주세요");
        }
        Comment comment = new Comment(postId,requestDto,username);
        commentRepository.save(comment);
    }

    //requestDto에는 게시글 번호와 수정할 댓글내용 , username은 로그인한 유저인지 체크하기 위한거
    //jwt를 사용할때에는 수정이 필요할듯 하다.

    public void updateComment(Long id , CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        comment.updateComment(requestDto);
    }
}
