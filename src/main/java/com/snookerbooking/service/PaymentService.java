package com.snookerbooking.service;

import com.snookerbooking.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO dto);

    List<PaymentDTO> getAllPayments();

    PaymentDTO getPaymentById(Long id);
}
