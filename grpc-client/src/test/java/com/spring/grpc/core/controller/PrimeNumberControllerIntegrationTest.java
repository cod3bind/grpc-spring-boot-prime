package com.spring.grpc.core.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext
class PrimeNumberControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOKStatusAndEmptyListAfterProvidingZero() throws Exception {
        mockMvc.perform(get("/prime/{number}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOKStatusAndEmptyListAfterProvidingOne() throws Exception {
        mockMvc.perform(get("/prime/{number}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOKStatusAfterProvidingTwo() throws Exception {
        mockMvc.perform(get("/prime/{number}", 2)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("[2]")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOKStatusAfterProvidingPositiveInteger() throws Exception {
        mockMvc.perform(get("/prime/{number}", 5)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("[2,3,5]")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOKStatusAfterProvidingMaxValue() throws Exception {
        mockMvc.perform(get("/prime/{number}", 16944952)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}