package com.se1863.networkcompany.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.se1863.networkcompany.entity.Address;
import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Contract;
import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;
import com.se1863.networkcompany.entity.Transaction;
import com.se1863.networkcompany.exception.DuplicateInputException;
import com.se1863.networkcompany.exception.IllegalDateException;
import com.se1863.networkcompany.exception.ObjectNotFoundException;
import com.se1863.networkcompany.service.AddressService;
import com.se1863.networkcompany.service.ClientService;
import com.se1863.networkcompany.service.ContractService;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.OptionService;
import com.se1863.networkcompany.service.ServiceService;
import com.se1863.networkcompany.service.Utils;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class ManageContractController implements IPattern {

    ContractService contractService;
    ClientService clientService;
    OptionService optionService;
    AddressService addressService;
    ServiceService serviceService;

    // FOR MANAGE CONTRACT
    @GetMapping("/view-contract")
    public String viewContract(Model model, @RequestParam(required = false) String searchedContract) {
        // sut' user ra ngoai neu no F5 lam mat flash attribute
        if (model.getAttribute("searchedContractOptions") == null && searchedContract != null) {
            return "redirect:/admin/view-contract";
        }

        List<Contract> contracts = contractService.findAll();
        List<String> notPaidContracts = contractService.notPaidFOrOver1MonthContractId();
        List<Client> clients = (List<Client>) clientService.findAll();
        Map<Contract, Set<Option>> optionMap = new HashMap<>();
        contracts.stream()
                .forEach(contract -> {
                    Set<Option> options = optionService.findBycontractId(contract.getContractId());
                    optionMap.put(contract, options);
                });
        // for (Contract contract : contracts) {
        // Set<Option> options =
        // optionRepository.findBycontractId(contract.getContractId());
        // optionMap.put(contract, options);
        // }
        model.addAttribute("contractList", contracts);
        model.addAttribute("clientList", clients);
        model.addAttribute("optionMap", optionMap);
        model.addAttribute("notPaidContract", notPaidContracts);

        if (searchedContract != null) {
            Optional<Contract> wrappedContract = contracts.stream()
                    .filter(contract -> contract.getContractId().equals(searchedContract))
                    .findFirst();
            wrappedContract.ifPresent(contract -> {
                model.addAttribute("searchedContract", contract);
                model.addAttribute("searchedContractDevices", contract.getContractDevices());
            });
        }
        return "admin/view-contract";
    }

    @PostMapping("/search-contract")
    public String searchContractHandler(@RequestParam String contractID, RedirectAttributes redirectAttributes) {

        Contract contract = contractService.findById(contractID);
        Set<Option> options = optionService.findBycontractId(contractID);

        redirectAttributes.addFlashAttribute("searchedContractOptions", options);
        boolean extendable = options.stream()
                .allMatch(option -> option.getService().getStatus() == 1);

        if (contract != null) {
            contract.getOptionsAndContracts().stream()
                    .forEach(optionAndContract -> redirectAttributes.addFlashAttribute(
                            optionAndContract.getOption().getOptionId() + "status", optionAndContract.getStatus()));

            redirectAttributes.addAttribute("searchedContract", contract);
            redirectAttributes.addFlashAttribute("extendable", extendable);
        } else
            redirectAttributes.addAttribute("searchNotFound", "true");
        return "redirect:/admin/view-contract";
    }

    @PostMapping("/update-contract")
    public String updateContractHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {
        String contractId = reqParams.get("contractID");
        Contract contract = contractService.findById(contractId);
        boolean setContractEndDateStatus, setServiceStatus;
        try {
            setContractEndDateStatus = contractService.setContractEndDate(contract, reqParams);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("updateStatus", FAILED);
            return "redirect:/admin/view-contract";
        } catch (IllegalDateException e) {
            redirectAttributes.addFlashAttribute("updateStatus", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/view-contract";
        }

        setServiceStatus = contractService.setServiceStatus(contract, reqParams);
        List<String> disabledServiceOfContract = contractService.getDisabledServicesNameOfContract(contract);

        if (setServiceStatus || setContractEndDateStatus) {
            redirectAttributes.addFlashAttribute("disabledServiceOfContract", disabledServiceOfContract);
            redirectAttributes.addFlashAttribute("updateStatus", SUCCESS);
            redirectAttributes.addFlashAttribute("updatedContract", contractId);
        } else {
            redirectAttributes.addFlashAttribute("updateStatus", EMPTY);
        }
        return "redirect:/admin/view-contract";
    }

    @GetMapping("/create-contract")
    public String createContract(Model model) {
        Set<Client> clients = clientService.findAllOrderByTransactionStatusDesc();
        List<String> notPaidClients = contractService.notPaidFor3MonthClientId();
        List<Address> addresses = addressService.findAll();
        List<Service> services = serviceService.findAllOrderByStatus();
        
        model.addAttribute("availableServiceNum", serviceService.findAvailableServiceNum());
        model.addAttribute("notPaidFor3MonthClients", notPaidClients);
        model.addAttribute("clientList", clients);
        model.addAttribute("addressList", addresses);
        model.addAttribute("serviceList", services);

        return "admin/create-contract";
    }

    @GetMapping("/review-contract")
    public String reviewContract(Model model, RedirectAttributes redirectAttributes) {

        return "admin/review-contract";
    }

    @PostMapping("/review-contract")
    public String reviewContractHandler(@RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) throws ParseException {
        Contract reviewContract = null;
        Integer duration = 0;
        try {
            reviewContract = contractService.exstractContractFromReqParams(reqParams);
            List<String> chooseService = contractService.extractChoseServiceFromReqParams(reqParams);
            duration = Integer.parseInt(reqParams.get("duration"));
            // check duplication here
            Map<String, Option> serviceIdAndOption = optionService
                    .generateServiceIdAndOptionMapFromDuration(chooseService, duration);
            redirectAttributes.addFlashAttribute("startDate", reviewContract.getContractStartDate());
            redirectAttributes.addFlashAttribute("reviewContract", reviewContract);
            redirectAttributes.addFlashAttribute("serviceIdAndOption", serviceIdAndOption);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/create-contract";
        } catch (DuplicateInputException e) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/create-contract";
        } catch (ObjectNotFoundException e) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/create-contract";
        } catch (IllegalDateException e) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
            return "redirect:/admin/create-contract";
        } finally {
            redirectAttributes.addFlashAttribute("searchedClient", reqParams.get("searchClient"));
            redirectAttributes.addFlashAttribute("choseStartDate", Utils.extractDate(reqParams.get("contractStartDate")));
            redirectAttributes.addFlashAttribute("choseDuration", duration);
        }
        return "redirect:/admin/review-contract";
    }

    @PostMapping("/create-contract")
    public String createContractHandler(@Valid Contract contract, BindingResult result,
            @RequestParam Map<String, String> reqParams,
            RedirectAttributes redirectAttributes) {

        Contract savedContract = contractService.saveCreatedContract(contract, reqParams);

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", FAILED);
            redirectAttributes.addFlashAttribute("message",
                    "Created Contract " + savedContract.getContractId() + " failed.");
        }

        Transaction transaction = contractService.createTransactionForNewContract(savedContract);
        contractService.saveTransactionForNewContract(savedContract, transaction);

        redirectAttributes.addFlashAttribute("status", SUCCESS);
        redirectAttributes.addFlashAttribute("message",
                "Created Contract " + savedContract.getContractId() + " successfully with Transaction "
                        + transaction.getTransactionId() + ".");
        return "redirect:/admin/view-contract";
    }

}
