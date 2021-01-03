package com.spring.server.controller;

import com.spring.server.ServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static testutils.RequestGenerator.testValidGetRequest;
import static testutils.RequestGenerator.testValidPostRequestOk;

@AutoConfigureMockMvc
@SpringBootTest(classes = {ServerApplication.class})
@WebAppConfiguration
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createGame() throws Exception {
        testValidPostRequestOk(
                mockMvc,
                "/game/create",
                "GameController/createGame/create.json");
        testValidGetRequest(
                mockMvc,
                "/game/state?gameName=game123&password=password123",
                "GameController/createGame/state.json");
    }

    @Test
    void startGame() throws Exception {
        testValidPostRequestOk(
                mockMvc,
                "/game/create",
                "GameController/startGame/create.json");
        testValidPostRequestOk(
                mockMvc,
                "/game/join",
                "GameController/startGame/join.json");
        testValidGetRequest(
                mockMvc,
                "/game/state?gameName=game234&password=password234",
                "GameController/startGame/state.json");
    }
}
