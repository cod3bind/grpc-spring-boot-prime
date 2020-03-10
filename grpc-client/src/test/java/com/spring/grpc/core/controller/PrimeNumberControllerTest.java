package com.spring.grpc.core.controller;

import com.spring.grpc.core.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrimeNumberController controller;

    @Test
    public void shouldReturnBadRequestStatusAfterProvidingNegativeInteger() throws Exception {
        mockMvc.perform(get("/prime/{number}", -5)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldThrowExceptionAfterProvidingNegativeInteger() {
        assertThrows(InvalidNumberException.class, () -> controller.calculate(-5));
    }

    @Test
    public void shouldReturnBadRequestStatusAfterProvidingStringInsteadOfInteger() throws Exception {
        mockMvc.perform(get("/prime/{number}", "abc")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnNotFoundStatusAfterProvidingNullInsteadOfInteger() throws Exception {
        mockMvc.perform(get("/prime")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}