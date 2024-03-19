package com.example.community.dto;

import com.example.community.domain.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardReqDto {
    private String title;
    private String content;
    private String author;


    public Board toEntity(){
        return Board.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}
