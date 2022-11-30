package ru.gb.wordloader.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDto {

    private Long id;
    private String original;
    private String translated;
}
