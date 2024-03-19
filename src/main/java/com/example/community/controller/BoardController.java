package com.example.community.controller;

import com.example.community.dto.BoardReqDto;
import com.example.community.dto.BoardResDto;
import com.example.community.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BoardReqDto boardReqDto){
        try{
            boardService.create(boardReqDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("작성 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생: " + e.getMessage());
        }

    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardResDto>> list(){
        List<BoardResDto> boardResDtoList = boardService.findAll();
        return ResponseEntity.ok(boardResDtoList);

    }

    @GetMapping("/find")
    public ResponseEntity<BoardResDto> find(@RequestParam Long boardId){
        BoardResDto boardResDtos = boardService.findById(boardId);
        return ResponseEntity.ok(boardResDtos);
    }


    @PutMapping("/update/{boardId}")
    public ResponseEntity<?> update(@PathVariable("boardId") Long boardId, @RequestBody BoardReqDto boardReqDto){
        try{
            boardService.update(boardId, boardReqDto);
            return ResponseEntity.status(HttpStatus.OK).body("수정 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 안됨");
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long boardId){
        try{
            boardService.delete(boardId);
            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 안됨");
        }
    }


}
