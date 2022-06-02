package com.sparta.week4hw.service;

import com.sparta.week4hw.dto.CommentRequestDto;
import com.sparta.week4hw.model.Board;
import com.sparta.week4hw.repository.boardRepository;
import com.sparta.week4hw.dto.boardRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class boardService {

    private final boardRepository boardrepository;

    @Transactional
    public Long update(Long id, boardRequestDto requestDto){
        Board board = boardrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        board.update(requestDto);
        return board.getId();
    }


}
