package com.spring.grpc.core.controller;

import com.spring.grpc.core.model.PrimeNumberServiceGrpc;
import com.spring.grpc.core.model.Request;
import com.spring.grpc.core.model.Response;
import com.spring.grpc.core.service.PrimeNumberService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

@Slf4j
@GRpcService
public class GrpcController extends PrimeNumberServiceGrpc.PrimeNumberServiceImplBase {

    private final PrimeNumberService primeNumberService;

    public GrpcController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @Override
    public void calculate(Request request, StreamObserver<Response> responseObserver) {
        log.info("Received message -> {}", request.toString());
        Response response = primeNumberService.calculate(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
