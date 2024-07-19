package com.se1863.networkcompany.service;

import java.util.List;
import java.util.Set;

import com.se1863.networkcompany.entity.Contract;
import com.se1863.networkcompany.entity.ContractAndOption;
import com.se1863.networkcompany.entity.Option;

public interface ContractAndOptionService {
    List<ContractAndOption> findAll();

    ContractAndOption save(ContractAndOption contractAndOption);
    
    Set<ContractAndOption> generateContractAndOptionSet(Contract contract, Set<Option> options);
}
