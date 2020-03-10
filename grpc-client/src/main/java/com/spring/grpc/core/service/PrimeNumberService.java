package com.spring.grpc.core.service;

import com.spring.grpc.core.model.PrimeNumberServiceGrpc;
import com.spring.grpc.core.model.Request;
import com.spring.grpc.core.model.Response;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PrimeNumberService {

    @Value("${grpc.host}")
    private String hostName;

    @Value("${grpc.port}")
    private int port;

    public List<Integer> getPrimeNumberResponse(int number) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(hostName, port)
                .usePlaintext()
                .build();

        PrimeNumberServiceGrpc.PrimeNumberServiceBlockingStub stub =
                PrimeNumberServiceGrpc.newBlockingStub(channel);

        Request request = Request.newBuilder()
                .setNumber(number)
                .build();

        Response response = stub.calculate(request);

        channel.shutdown();
        return response.getPrimeNumbersList();
    }

}
