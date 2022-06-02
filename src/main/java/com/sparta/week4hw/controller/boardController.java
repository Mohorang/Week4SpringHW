package com.sparta.week4hw.controller;


import com.sparta.week4hw.model.Board;
import com.sparta.week4hw.repository.boardRepository;
import com.sparta.week4hw.dto.boardRequestDto;
import com.sparta.week4hw.service.boardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class boardController {

    private final boardRepository boardrepository;
    private final boardService boardservice;
    //전체 게시글 목록 조회 API
    @GetMapping("/post/see")
    public List<Board> getPosts(){
        //전체 게시물 목록을 작성 날짜 기준으로 내림차순 정렬하기
        return boardrepository.findAll();
    }

    @GetMapping("/post/see/{id}")
    public Board getPosts(@PathVariable Long id){
        //전체 게시물 목록을 작성 날짜 기준으로 내림차순 정렬하기
        return boardrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    //repository의 save메소드가 entity를 리턴하기때문에
    //post의 리턴값 형태를 Entity클래스로 만들었다.
    //게시글 작성 API
    @PostMapping("/post/detail/posting")
    public Board createPost(@RequestBody boardRequestDto requestDto){
        Board board = new Board(requestDto);
        return boardrepository.save(board);
    }

    //게시글 수정 API
    @PutMapping("/post/modify/{id}")
    public Long updatePost(@PathVariable Long id,@RequestBody boardRequestDto requestDto){
        Board board = boardrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(board.getPassword().equals(requestDto.getPassword())) {
            boardservice.update(id, requestDto);
            return id;
        }
        else return 0L;
    }

    //게시글 삭제 API
    @DeleteMapping("/post/delete/{id}")
    public Long deletePost(@PathVariable Long id,@RequestBody boardRequestDto requestDto){
        //아이디가 존재하는지 체크
        Board board = boardrepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        //넘겨받은 id의 패스워드와 requestDto에 들어간 패스워드가 일치할때 id를 리턴
        if(board.getPassword().equals(requestDto.getPassword())){
            boardrepository.deleteById(id);
            return id;
        }
        //일치하지않을시에는 0을 리턴하고 이에 대한 처리를 추가해준다.
        else return 0L;
    }


}