package com.se1863.networkcompany.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Payment;
import com.se1863.networkcompany.repository.ContractRepository;
import com.se1863.networkcompany.service.ClientService;
import com.se1863.networkcompany.service.PaymentService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")

public class ManagePaymentController {
    ClientService clientService;
    PaymentService paymentService;
    ContractRepository contractRepository;

    @GetMapping("/manage-payment")
    public String viewPayment(Model model) {
        List<Client> clients = (List<Client>) clientService.findAll();
        List<Payment> payments = paymentService.findAllOrderByDate();
        List<String> notPaidClients = contractRepository.notPaidFor3MonthClientId();

        Map<String, Client> clientAndPaymentMap = paymentService.loadPaymentAndClientMap();
        model.addAttribute("clientList", clients);
        model.addAttribute("paymentList", payments);
        model.addAttribute("paymentAndClientMap", clientAndPaymentMap);
        model.addAttribute("notPaidFor3MonthClients", notPaidClients);
        return "admin/manage-payment";
    }
}
