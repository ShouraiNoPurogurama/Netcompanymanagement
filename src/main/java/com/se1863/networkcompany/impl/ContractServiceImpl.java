package com.se1863.networkcompany.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Contract;
import com.se1863.networkcompany.entity.ContractAndOption;
import com.se1863.networkcompany.entity.Device;
import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Transaction;
import com.se1863.networkcompany.exception.BlankInputException;
import com.se1863.networkcompany.exception.IllegalDateException;
import com.se1863.networkcompany.exception.ObjectNotFoundException;
import com.se1863.networkcompany.repository.ClientRepository;
import com.se1863.networkcompany.repository.ContractAndOptionRepository;
import com.se1863.networkcompany.repository.ContractRepository;
import com.se1863.networkcompany.repository.OptionRepository;
import com.se1863.networkcompany.repository.TransactionRepository;
import com.se1863.networkcompany.service.ClientService;
import com.se1863.networkcompany.service.ContractAndOptionService;
import com.se1863.networkcompany.service.ContractService;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.OptionService;
import com.se1863.networkcompany.service.Utils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService, IPattern {
    ContractRepository contractRepository;
    ContractAndOptionRepository contractAndOptionRepository;
    OptionRepository optionRepository;
    ClientRepository clientRepository;
    TransactionRepository transactionRepository;
    OptionService optionService;
    ClientService clientService;
    ContractAndOptionService contractAndOptionService;

    DeviceServiceImpl deviceServiceImpl;

    @Override
    public Contract findById(String id) {
        return contractRepository.findById(id).get();
    }

    @Override
    public String findLastId() {
        return contractRepository.findLastId();
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
        
    }

    @Override
    public List<String> notPaidFOrOver1MonthContractId() {
        return contractRepository.notPaidFOrOver1MonthContractId();
    }

    @Override
    public List<String> notPaidFor3MonthClientId() {
        return contractRepository.notPaidFor3MonthClientId();
    }

    @Override
    public List<String> notPaidFor3MonthContractsId() {
        return contractRepository.notPaidFor3MonthContractsId();
    }

    @Override
    public List<Contract> findAllByClient(Client client) {
        return contractRepository.findAllByClient(client);
    }

    @Override
    public int extractIdNumber(String constractId) {
        Pattern pat = Pattern.compile(constractIdPat);
        Matcher mat = pat.matcher(constractId);
        String idNumStr = null;
        if (mat.find()) {
            idNumStr = mat.group("number");
        }
        int idNumber = Integer.parseInt(idNumStr);
        return idNumber;
    }

    @Override
    public String generateNewId() {
        String id = findLastId();
        if (!id.matches(constractIdPat))
            throw new BlankInputException("Constract ID is invalid.");
        int idNumber = extractIdNumber(id);
        String newIdNumStr = String.valueOf(++idNumber);
        while (newIdNumStr.length() < 3) {
            newIdNumStr = "0" + newIdNumStr;
        }
        String result = "CON" + newIdNumStr;
        return result;
    }

    @Override
    public Map<String, String> extractOptionStatusFromReqAtt(Map<String, String> reqParams) {
        Pattern pattern = Pattern.compile(reqParamServiceIdPat);
        Matcher mat;
        Map<String, String> optionStatus = new HashMap<>();
        for (String key : reqParams.keySet()) {
            mat = pattern.matcher(key);
            if (mat.find()) {
                String extractedOptionId = mat.group("id");
                optionStatus.put(extractedOptionId, reqParams.get(key));
            }
        }
        return optionStatus;
    }

    @Override
    public boolean setServiceStatus(Contract contract, Map<String, String> reqParams) {
        // component
        Set<ContractAndOption> options = contract.getOptionsAndContracts();
        Map<String, String> optionStatus = extractOptionStatusFromReqAtt(reqParams);
        boolean[] statusChanged = { false }; // use arr to be able to set its value in stream
        options.stream()
                .forEach(option -> {
                    int currStatus = option.getStatus();
                    option.setStatus(optionStatus.get(option.getOption().getOptionId()).equals("enable") ? 1 : 0);
                    contractAndOptionRepository.save(option);
                    statusChanged[0] = (currStatus != option.getStatus() || statusChanged[0] == true);
                });
        return statusChanged[0];
    }

    @Override
    public boolean setContractEndDate(Contract contract, Map<String, String> reqParams)
            throws ParseException, IllegalDateException {
        if (reqParams.get("newContractEndDate").isBlank()) {
            return false;
        }
        String newContractEndDateStr = reqParams.get("newContractEndDate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date newContractEndDate = null;
        // component
        newContractEndDate = formatter.parse(newContractEndDateStr);
        if (newContractEndDate.before(contract.getContractEndDate()))
            throw new IllegalDateException("New end date must be after the current one.");
        contract.setContractEndDate(newContractEndDate);
        contractRepository.save(contract);
        
        return true;
    }

    @Override
    // (serviceId_..., OptionId)
    public List<String> extractChoseServiceFromReqParams(Map<String, String> reqParams) {
        List<String> result = reqParams.keySet().stream()
                .filter(key -> key.matches(createServiceIdPat))
                .map(key -> reqParams.get(key))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Contract exstractContractFromReqParams(@RequestParam Map<String, String> reqParams)
            throws ParseException, ObjectNotFoundException, BlankInputException {
        String clientId = reqParams.get("searchClient");
        List<String> serviceId = extractChoseServiceFromReqParams(reqParams);
        String duration = reqParams.get("duration");
        String contractStartDateStr = reqParams.get("contractStartDate");
        if (clientId == null || clientId.isBlank() || serviceId.size() < 1 || duration == null
                || contractStartDateStr == null)
            throw new BlankInputException("Please fill all the input.");

        Client client = clientService.findById(clientId);

        Date contractStartDate = Utils.extractDate(contractStartDateStr);
        Date contractEndDate = Utils.addDateByMonth(contractStartDate, duration);
        // check if start date is valid
        Utils.validStartAndEndDate(contractStartDate, contractEndDate);
        Contract contract = new Contract(contractStartDate, contractEndDate, client);
        String newConstractId = generateNewId();
        contract.setContractId(newConstractId);
        return contract;
    }

    @Override
    public Contract saveCreatedContract(Contract contract, Map<String, String> reqParams) {
        Set<Option> options = optionService.extractOptionSetFromReqParams(reqParams);
        Set<Device> devices = deviceServiceImpl.extractDeviceSetFromOptionSet(options);
        Client client = clientService.extractClientFromReqParams(reqParams);
        contract.setClient(client);
        Set<ContractAndOption> contractAndOptions = contractAndOptionService.generateContractAndOptionSet(contract,
                options);
        // save contract before fully set OptionsAndContracts to prevent exception
        save(contract);
        
        contractAndOptions.stream()
                .forEach(value -> contractAndOptionService.save(value));
        contract.setOptionsAndContracts(contractAndOptions);
        contract.setContractDevices(devices);
        return save(contract);
        
    }

    public List<String> getDisabledServicesNameOfContract(Contract contract) {
        Set<ContractAndOption> options = contract.getOptionsAndContracts();
        List<String> result = options.stream()
                .filter(contractAndOption -> contractAndOption.getStatus() == 0)
                .map(contractAndOption -> contractAndOption.getOption().getService().getServiceName())
                .collect(Collectors.toList());
        return result;
    }

    public String findLastTransactionId() {
        return transactionRepository.findLastTransactionId();
    }

    public String generateNewTransactionId(String lastTransactionId) {
        Pattern pat = Pattern.compile(transactionIdPat);
        Matcher mat = pat.matcher(lastTransactionId);
        int idNum = 0;
        if(mat.find()) {
            idNum = Integer.parseInt(mat.group("number"));
        }
        String idNumStr = String.valueOf(++idNum);
        while (idNumStr.length() < 3) {
            idNumStr = "0" + idNumStr;
        }
        String result = "TRAN" + idNumStr;
        return result;
    }

    public Transaction createTransactionForNewContract(Contract savedContract) {
        Integer totalPriceOfContract = contractRepository.findTotalPriceFromContractId(savedContract.getContractId());
        Transaction transaction = new Transaction(savedContract.getContractStartDate(), totalPriceOfContract, 1);
        String newTransactionId = generateNewTransactionId(findLastTransactionId());
        transaction.setTransactionId(newTransactionId);
        transaction.setContract(savedContract);
        transaction.setDescription("Created by new contact");
        transactionRepository.save(transaction);
        return transaction;
    }

    public void saveTransactionForNewContract(Contract savedContract, Transaction transaction) {
        Set<Transaction> trans = new HashSet<>();
        trans.add(transaction);
        savedContract.setContractTransactions(trans);
        save(savedContract);
    }
}
