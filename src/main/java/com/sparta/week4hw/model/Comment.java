package com.sparta.week4hw.model;

import com.sparta.week4hw.dto.CommentRequestDto;
import com.sparta.week4hw.repository.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    //게시글 ID
    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    public Comment(Long postId ,CommentRequestDto requestDto,String username){
        this.postId = postId;
        this.comment = requestDto.getComment();
        this.username = username;
    }
    public void updateComment(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }

}
