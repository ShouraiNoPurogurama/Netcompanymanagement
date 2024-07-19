package com.se1863.networkcompany.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se1863.networkcompany.entity.Address;
import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Contract;
import com.se1863.networkcompany.service.AddressService;
import com.se1863.networkcompany.service.ClientService;
import com.se1863.networkcompany.service.ContractService;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.MailSenderService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ManageClientController implements IPattern {
    @Autowired
    ClientService clientService;
    AddressService addressService;
    ContractService contractService;
    MailSenderService mailSenderService;

    @GetMapping("/manage-client")
    public String manageClient(Model model, @RequestParam(required = false) String searchedClient,
            @ModelAttribute("unblockable") String unblockable, @ModelAttribute("blocked") String blocked,
            @ModelAttribute("unblocked") String unblocked) 
{
        Set<Client> clients = clientService.findAllOrderByTransactionStatusAsc();
        List<Address> addresses = addressService.findAll();
        List<String> notPaidClients = contractService.notPaidFor3MonthClientId();
        List<String> notPaidOver1MonthClients = clientService.notPaidForOver1MonthClients();
        Map<String, List<?>> attributes = new HashMap<>();
        attributes.put("notPaidFor3MonthClients", notPaidClients);
        attributes.put("addressList", addresses);
        attributes.put("notPaidOver1MonthClients", notPaidOver1MonthClients);
        model.addAllAttributes(attributes);
        model.addAttribute("clientList", clients);
        Optional<Client> searched = clients.stream()
                .filter(client -> client.getClientId().equals(searchedClient))
                .findFirst();
        searched.ifPresent(client -> {
            List<Contract> contracts = contractService.findAllByClient(client);
            model.addAttribute("searchedClientContracts", contracts);
            model.addAttribute("searchedClient", client);
        });
        return "admin/manage-client";
    }

    @PostMapping("/block-client")
    public String blockClientHandler(@RequestParam String clientID, @RequestParam(required = false) String search,
            RedirectAttributes redirectAttributes) {

        Client client = clientService.findById(clientID);
        List<String> notPaidContracts = contractService.notPaidFor3MonthContractsId();
        // If request from the search page
        if (search != null && search != "true") {
            redirectAttributes.addAttribute("searchedClient", client);
        }
        // kiem tra client co the bi block hay khong
        if (client.getIsBlocked() == 0) {
            notPaidContracts.stream()
                    .map(id -> contractService.findById(id))
                    .filter(contract -> contract.getClient().getClientId().equals(clientID))
                    .findFirst()
                    .ifPresentOrElse(
                            contract -> {
                                clientService.blockAndSave(client);
                                mailSenderService.sendEmail(client.getEmail(), blockAccountNoti, "", ACCOUNT_BLOCKED);
                                redirectAttributes.addFlashAttribute("blocked", clientID);
                            }, () -> redirectAttributes.addFlashAttribute("unblockable", clientID));
            clientService.disableAllClientRequests(clientID);
            // for (String contractId : notPaidContracts) {
            // Contract contract = contractService.findById(contractId);
            // if (contract.getClient().getClientId().equals(clientID)) {
            // clientService.blockAndSave(client);
            // redirectAttributes.addFlashAttribute("blocked", clientID);
            // } else {
            // redirectAttributes.addFlashAttribute("unblockable", clientID);
            // }
            // return "redirect:/admin/manage-client";
            // }
        } else {
            clientService.unblockAndSave(client);
            redirectAttributes.addFlashAttribute("unblocked", clientID);
        }
        return "redirect:/admin/manage-client";
    }

    @PostMapping("/search-client")
    public String searchClientHandler(@RequestParam String searchInput, RedirectAttributes redirectAttributes) {
        Client client = clientService.findByPhoneNumber(searchInput);
        if (client != null) {
            redirectAttributes.addAttribute("searchedClient", client);
        } else {
            redirectAttributes.addAttribute("searchNotFound", "true");
        }
        return "redirect:/admin/manage-client";
    }
}
