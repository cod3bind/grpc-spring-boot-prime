package com.spring.grpc.core.controller;

import com.spring.grpc.core.model.PrimeNumberServiceGrpc;
import com.spring.grpc.core.model.Request;
import com.spring.grpc.core.model.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@DirtiesContext
class GrpcServerIntegrationTest {

    private PrimeNumberServiceGrpc.PrimeNumberServiceBlockingStub smsServiceStub;

    @BeforeEach
    void setup() {
        String hostName = "localhost";
        int port = 6565;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(hostName, port)
                .usePlaintext()
                .build();

        smsServiceStub = PrimeNumberServiceGrpc.newBlockingStub(channel);
    }

    @Test
    public void shouldReturnEmptyListAfterProvidingZero() {
        // given
        Request request = Request.newBuilder().setNumber(0).build();

        // when
        Response response = smsServiceStub.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(0, response.getPrimeNumbersList().size());
    }

    @Test
    public void shouldReturnEmptyListAfterProvidingOne() {
        // given
        Request request = Request.newBuilder().setNumber(1).build();

        // when
        Response response = smsServiceStub.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(0, response.getPrimeNumbersList().size());
    }

    @Test
    public void shouldReturnNotEmptyListAfterProvidingTwo() {
        // given
        Request request = Request.newBuilder().setNumber(2).build();

        // when
        Response response = smsServiceStub.calculate(request);

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
        Response response = smsServiceStub.calculate(request);

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
        Response response = smsServiceStub.calculate(request);

        // then
        assertNotNull(response);
        assertEquals(1087960, response.getPrimeNumbersList().size());
        assertEquals(2, response.getPrimeNumbersList().get(0));
        assertEquals(3, response.getPrimeNumbersList().get(1));
        assertEquals(5, response.getPrimeNumbersList().get(2));
    }
}
