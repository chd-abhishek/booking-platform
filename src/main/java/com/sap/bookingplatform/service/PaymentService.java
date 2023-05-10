package com.sap.bookingplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sap.bookingplatform.data.entities.Payment;
import com.sap.bookingplatform.data.enums.PaymentStatus;
import com.sap.bookingplatform.data.repositories.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepo;

    public List<Payment> getAllPayment() {
        return paymentRepo.findAll();
    }

    public Payment makePayment(Payment payment) {
        payment.setStatus(PaymentStatus.DONE);
        log.debug("Payment {} is done", payment.getId());
        return paymentRepo.save(payment);
    }
}
