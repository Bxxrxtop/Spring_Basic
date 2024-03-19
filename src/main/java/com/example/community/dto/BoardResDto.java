package com.example.community.dto;

import com.example.community.domain.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardResDto {
    private String title;
    private String content;
    private String author;

    public BoardResDto(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getAuthor();
    }
}
