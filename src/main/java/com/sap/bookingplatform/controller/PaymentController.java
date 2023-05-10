package com.sap.bookingplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sap.bookingplatform.data.entities.Payment;
import com.sap.bookingplatform.service.PaymentService;

@RestController
@RequestMapping("/platform")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment")
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayment();
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.makePayment(payment));
    }

}
