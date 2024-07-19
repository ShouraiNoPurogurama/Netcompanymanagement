package com.se1863.networkcompany.service;

import java.util.List;
import java.util.Map;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Payment;


public interface PaymentService {
    List<Payment> findAllPaymentOrderByDate();
    List<Payment> findAllOrderByDate();
    
    Map<String, Client> loadPaymentAndClientMap();

}
