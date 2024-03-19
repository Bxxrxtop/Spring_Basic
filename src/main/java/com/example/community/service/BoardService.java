package com.example.community.service;

import com.example.community.domain.Entity.Board;
import com.example.community.dto.BoardReqDto;
import com.example.community.dto.BoardResDto;
import com.example.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public void create(BoardReqDto boardReqDto){
        Board board = boardReqDto.toEntity();
        boardRepository.save(board);
    }

    public List<BoardResDto> findAll(){
        List<Board> boards = boardRepository.findAll();

        List<BoardResDto> boardResDtos = boards.stream().map(board -> new BoardResDto(board))
                .collect(Collectors.toList());

        return boardResDtos;
    }

    public BoardResDto findById(Long id){
        Optional<Board> board = boardRepository.findById(id);
        BoardResDto boardResDto = new BoardResDto(board.get());
        return boardResDto;
    }

    public BoardReqDto update(Long id, BoardReqDto boardReqDto){
        Optional<Board> board = boardRepository.findById(id);
        if(board.isPresent()){
            Board _board = board.get();
            _board.setTitle(boardReqDto.getTitle());
            _board.setContent(boardReqDto.getContent());
            _board.setAuthor(boardReqDto.getAuthor());
            boardRepository.save(_board);
        }
        else {
            return null;
        }
        return null;
    }

    public void delete(Long id){
        Optional<Board> board = boardRepository.findById(id);
        try{
            boardRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    public BoardResDto findByAuthor(String author){
//        Optional<Board> board = boardRepository.findByAuthor(author)
//    }
}
