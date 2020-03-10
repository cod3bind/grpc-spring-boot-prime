package com.spring.grpc.core.service;

import com.spring.grpc.core.model.Request;
import com.spring.grpc.core.model.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PrimeNumberServiceTest {

    private final AlgorithmProvider algorithmProvider = new SieveOfEratosthenesService();
    private final PrimeNumberService primeNumberService = new PrimeNumberService(algorithmProvider);

    @Test
    public void shouldReturnEmptyListAfterProvidingZero() {
        // given
        Request request = Request.newBuilder().setNumber(0).build();

        // when
        Response response = primeNumberService.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(0, response.getPrimeNumbersList().size());
    }

    @Test
    public void shouldReturnEmptyListAfterProvidingOne() {
        // given
        Request request = Request.newBuilder().setNumber(1).build();

        // when
        Response response = primeNumberService.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(0, response.getPrimeNumbersList().size());
    }

    @Test
    public void shouldReturnNotEmptyListAfterProvidingTwo() {
        // given
        Request request = Request.newBuilder().setNumber(2).build();

        // when
        Response response = primeNumberService.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(1, response.getPrimeNumbersList().size());
        assertEquals(2, response.getPrimeNumbersList().get(0));
    }

    @Test
    public void shouldReturnNotEmptyListAfterProvidingPositiveInteger() {
        // given
        Request request = Request.newBuilder().setNumber(5).build();

        // when
        Response response = primeNumberService.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(3, response.getPrimeNumbersList().size());
        assertEquals(2, response.getPrimeNumbersList().get(0));
        assertEquals(3, response.getPrimeNumbersList().get(1));
        assertEquals(5, response.getPrimeNumbersList().get(2));
    }

    @Test
    public void shouldReturnNotEmptyListAfterProvidingMaxValue() {
        // given
        Request request = Request.newBuilder().setNumber(16944952).build();

        // when
        Response response = primeNumberService.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(1087960, response.getPrimeNumbersList().size());
        assertEquals(2, response.getPrimeNumbersList().get(0));
        assertEquals(3, response.getPrimeNumbersList().get(1));
        assertEquals(5, response.getPrimeNumbersList().get(2));
    }

}