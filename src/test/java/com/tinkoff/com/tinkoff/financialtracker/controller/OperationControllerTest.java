package com.tinkoff.com.tinkoff.financialtracker.controller;

import com.tinkoff.com.tinkoff.financialtracker.controllers.OperationController;
import com.tinkoff.com.tinkoff.financialtracker.service.OperationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebMvcTest(controllers = OperationController.class)
public class OperationControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private OperationService operationsService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @DisplayName("Return 400 code if missing required parameter")
    void addTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/wallet/operation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"walletId\":\"1\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
