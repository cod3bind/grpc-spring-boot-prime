package com.spring.grpc.core.service;

import com.spring.grpc.core.model.Request;
import com.spring.grpc.core.model.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimeNumberService {
    private final AlgorithmProvider algorithmProvider;

    public PrimeNumberService(AlgorithmProvider algorithmProvider) {
        this.algorithmProvider = algorithmProvider;
    }

    public Response calculate(final Request request) {
        List<Integer> primeNumbers = algorithmProvider.getPrimeNumbers(request.getNumber());
        return Response.newBuilder()
                .addAllPrimeNumbers(primeNumbers)
                .build();
    }
}
