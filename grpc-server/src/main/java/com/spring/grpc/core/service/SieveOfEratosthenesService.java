package com.spring.grpc.core.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class SieveOfEratosthenesService implements AlgorithmProvider {

    @Override
    public List<Integer> getPrimeNumbers(int number) {
        boolean[] prime = new boolean[number + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= number; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= number; i += p) {
                    prime[i] = false;
                }
            }
        }

        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= number; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
}
