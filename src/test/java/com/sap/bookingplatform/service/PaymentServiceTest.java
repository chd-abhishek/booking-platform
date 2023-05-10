package com.sap.bookingplatform.service;

import com.sap.bookingplatform.data.entities.Payment;
import com.sap.bookingplatform.data.repositories.PaymentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.SpringVersion;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PaymentService.class})
@ExtendWith(SpringExtension.class)
class PaymentServiceTest {
    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentService paymentService;

    /**
     * Method under test: {@link PaymentService#getAllPayment()}
     */
    @Test
    void testGetAllPayment() {
        assertNotNull(paymentService.getAllPayment());
    }

    /**
     * Method under test: {@link PaymentService#makePayment(Payment)}
     */
    @Test
    void testMakePayment() {
        Payment payment = new Payment();
        when(paymentRepository.save(payment)).thenReturn(payment);
        assertNotNull(paymentService.makePayment(payment));
    }
}

