package com.se1863.networkcompany.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Payment;
import com.se1863.networkcompany.repository.ClientRepository;
import com.se1863.networkcompany.repository.PaymentRepository;
import com.se1863.networkcompany.service.PaymentService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    PaymentRepository paymentRepository;
    ClientRepository clientRepository;

    @Override
    public List<Payment> findAllPaymentOrderByDate() {
        List<Payment> payments = paymentRepository.findAllPaymentOrderByDate();
        return payments;
    }

    @Override
    public Map<String, Client> loadPaymentAndClientMap() {
        List<Payment> payments = paymentRepository.findAll();
        Map<String, Client> paymentAndClientMap = new HashMap<>();
        payments.stream()
                .forEach(payment -> {
                    String clientId = clientRepository.findClientIdByPaymentId(payment.getPaymentId());
                    Client client = clientRepository.findById(clientId).get();
                    paymentAndClientMap.put(payment.getPaymentId(), client);
                });
        return paymentAndClientMap;
    }

    public List<Payment> findAllOrderByDate() {
        return paymentRepository.findAllOrderByDate();
    }
}
