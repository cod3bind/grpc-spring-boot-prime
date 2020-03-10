package com.spring.grpc.core.service;

import java.util.List;

public interface AlgorithmProvider {

    List<Integer> getPrimeNumbers(int number);
}
