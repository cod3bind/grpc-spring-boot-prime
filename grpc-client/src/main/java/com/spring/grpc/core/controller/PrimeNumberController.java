package com.spring.grpc.core.controller;

import com.spring.grpc.core.exception.InvalidNumberException;
import com.spring.grpc.core.service.PrimeNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prime")
@Slf4j
public class PrimeNumberController {

    private final PrimeNumberService primeNumberService;

    public PrimeNumberController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity<?> calculate(@PathVariable("number") int number) {
        if (number < 0 || number > 16944952) {
            throw new InvalidNumberException("{number} must be an Integer number and greater than zero and less than 16944953");
        }
        List<Integer> primeNumberResponse = primeNumberService.getPrimeNumberResponse(number);
        return ResponseEntity.ok(primeNumberResponse);
    }
}
