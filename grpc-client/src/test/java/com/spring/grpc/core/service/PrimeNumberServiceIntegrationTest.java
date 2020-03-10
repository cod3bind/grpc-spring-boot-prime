package com.spring.grpc.core.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@DirtiesContext
@SpringBootTest
class PrimeNumberServiceIntegrationTest {

    @Autowired
    private PrimeNumberService primeNumberService;

    @Test
    public void shouldReturnEmptyListAfterProvidingZero() {
        //given
        int number = 0;

        //when
        List<Integer> primeNumberResponse = primeNumberService.getPrimeNumberResponse(number);

        //then
        assertThat(primeNumberResponse.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnEmptyListAfterProvidingOne() throws Exception {
        //given
        int number = 1;

        //when
        List<Integer> primeNumberResponse = primeNumberService.getPrimeNumberResponse(number);

        //then
        assertThat(primeNumberResponse.size()).isEqualTo(0);
    }

    @Test
    public void shouldReturnOKStatusAndEmptyListAfterProvidingTwo() throws Exception {
        //given
        int number = 2;

        //when
        List<Integer> primeNumberResponse = primeNumberService.getPrimeNumberResponse(number);

        //then
        assertThat(primeNumberResponse.size()).isEqualTo(1);
        assertThat(primeNumberResponse.get(0)).isEqualTo(2);
    }

    @Test
    public void shouldReturnOKStatusAfterProvidingPositiveInteger() throws Exception {
        //given
        int number = 5;

        //when
        List<Integer> primeNumberResponse = primeNumberService.getPrimeNumberResponse(number);

        //then
        assertThat(primeNumberResponse.size()).isEqualTo(3);
        assertThat(primeNumberResponse.get(0)).isEqualTo(2);
        assertThat(primeNumberResponse.get(1)).isEqualTo(3);
        assertThat(primeNumberResponse.get(2)).isEqualTo(5);
    }

    @Test
    public void shouldReturnOKStatusAfterProvidingMaxValue() throws Exception {
        //given
        int number = 16944952;

        //when
        List<Integer> primeNumberResponse = primeNumberService.getPrimeNumberResponse(number);

        //then
        assertThat(primeNumberResponse.size()).isEqualTo(1087960);
    }
}