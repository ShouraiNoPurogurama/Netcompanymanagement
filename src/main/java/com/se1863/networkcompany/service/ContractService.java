package com.se1863.networkcompany.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Contract;
import com.se1863.networkcompany.entity.Transaction;
import com.se1863.networkcompany.exception.BlankInputException;
import com.se1863.networkcompany.exception.IllegalDateException;
import com.se1863.networkcompany.exception.ObjectNotFoundException;

public interface ContractService {
    void saveTransactionForNewContract(Contract savedContract, Transaction transaction);
    
    boolean setServiceStatus(Contract contract, Map<String, String> reqParams);
    boolean setContractEndDate(Contract contract, Map<String, String> reqParams) throws ParseException, IllegalDateException;

    int extractIdNumber(String constractId);
    
    String findLastId();
    String generateNewId();

    Contract findById(String id);
    Contract save(Contract contract);
    Contract exstractContractFromReqParams(Map<String, String> reqParams) throws ParseException, ObjectNotFoundException, BlankInputException;
    Contract saveCreatedContract(Contract contract, Map<String, String> reqParams);

    Transaction createTransactionForNewContract(Contract savedContract);

    List<Contract> findAll();
    List<String> notPaidFOrOver1MonthContractId();
    List<String> notPaidFor3MonthClientId();
    List<String> notPaidFor3MonthContractsId();
    List<String> getDisabledServicesNameOfContract(Contract contract);
    List<Contract> findAllByClient(Client client);
    List<String> extractChoseServiceFromReqParams(Map<String, String> reqParams);

    Map<String, String> extractOptionStatusFromReqAtt(Map<String, String> reqParams);
}
