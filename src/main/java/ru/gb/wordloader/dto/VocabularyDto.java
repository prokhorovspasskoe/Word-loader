package ru.gb.wordloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocabularyDto {

    private Long id;
    private String theme;
    private boolean isPrivate;
    private UserDto user;
    private List<WordDto> words;
}
