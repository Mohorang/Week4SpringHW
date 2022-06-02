package com.sparta.week4hw.repository;

import com.sparta.week4hw.model.Board;
import com.sparta.week4hw.model.Comment;
import com.sparta.week4hw.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface commentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();
    List<Comment> findAllByPostId(Long postId);
    Optional<Comment> findByUsername(String username);
}
