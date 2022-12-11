package ru.gb.wordloader.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.gb.wordloader.conrtollers.RegistrationController;
import ru.gb.wordloader.dto.RegistrationUserDto;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.services.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@AutoConfigureMockMvc
@SpringBootTest
public class RegistrationControllerTest {


    MockMvc mockMvc;
    RegistrationController registrationController;
    UserService userService;

    @Autowired
    public RegistrationControllerTest(MockMvc mockMvc, RegistrationController registrationController, UserService userService) {
        this.mockMvc = mockMvc;
        this.registrationController = registrationController;
        this.userService = userService;
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
    @Order(1)
    void urlIsAccesible() throws Exception {
        mockMvc.perform(get("/api/v1/registration")).andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(2)
    //TODO удаление после тестов
    void registrationNewUser() throws Exception {
        User user = userService.findByName("test");
        if (user != null) {
            userService.deleteUser(user.getId());
        }
        RegistrationUserDto userDto = new RegistrationUserDto().builder()
                .username("test")
                .password("test")
                .matchingPassword("test")
                .build();
        mockMvc.perform(postToJson("/api/v1/registration/signup", userDto)).andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(3)
    //TODO сделать общего пользователя с другими тестами
    void registrationWithExistUsername() throws Exception {
        RegistrationUserDto userDto = new RegistrationUserDto().builder()
                .username("test7")
                .password("test7")
                .matchingPassword("test7")
                .build();
        mockMvc.perform(postToJson("/api/v1/registration/signup", userDto)).andExpect(status().is4xxClientError());
    }

    @Test
    void registationWithMismatchPass () throws Exception {
        RegistrationUserDto userDto = new RegistrationUserDto().builder()
                .username("test8")
                .password("test8")
                .matchingPassword("test7")
                .build();
        mockMvc.perform(postToJson("/api/v1/registration/signup", userDto)).andExpect(status().is4xxClientError());
    }
}

