package ru.gb.wordloader.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import ru.gb.wordloader.conrtollers.VocabularyController;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.services.PersonalAccountService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO создавать пользователя и логиниться перед тестами
@AutoConfigureMockMvc
@SpringBootTest
public class VocabularyControllerTest {

    PersonalAccountService personalAccountService;
    VocabularyController vocabularyController;
    MockMvc mockMvc;
    ObjectMapper objectMapper;


    @Autowired
    public VocabularyControllerTest(PersonalAccountService personalAccountService, VocabularyController vocabularyController, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.personalAccountService = personalAccountService;
        this.vocabularyController = vocabularyController;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    public static MockHttpServletRequestBuilder postToJson(String url, Object body) {
        try {
            String json = new ObjectMapper().writeValueAsString(body);
            return post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getVocabularyById() {
        String bodyContent = null;
        try {
            bodyContent = mockMvc.perform(get("/api/v1/vocabulary/get/2")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(new VocabularyDto())))
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        VocabularyDto vocabularyDto = null;
        try {
            vocabularyDto = objectMapper.readValue(bodyContent, VocabularyDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        vocabularyDto.toString();
        Assert.assertNotNull(vocabularyDto);
    }

    @Test
    @Order(1)
    public void addNewVocabulary() throws Exception {
        List<WordDto> wordDtoList = new ArrayList<>();
        wordDtoList.add(WordConverter.convertToDTO(personalAccountService.findWordById(1L)));
        VocabularyDto vocabularyDto= VocabularyDto.builder()
                .theme("test")
                .isPrivate(false)
                .words(wordDtoList)
                .build();
        mockMvc.perform(postToJson("/api/v1/vocabulary/add", vocabularyDto)).andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(2)
    public void deleteVocabulary() throws Exception {
        mockMvc.perform(delete("/api/v1/vocabulary/delete/3")).andExpect(status().is2xxSuccessful());
    }
}
