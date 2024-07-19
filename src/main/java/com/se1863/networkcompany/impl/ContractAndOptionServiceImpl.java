package com.se1863.networkcompany.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Contract;
import com.se1863.networkcompany.entity.ContractAndOption;
import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.repository.ContractAndOptionRepository;
import com.se1863.networkcompany.service.ContractAndOptionService;
import com.se1863.networkcompany.service.IPattern;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContractAndOptionServiceImpl implements ContractAndOptionService, IPattern {
    ContractAndOptionRepository contractAndOptionRepository;
    @Override
    public List<ContractAndOption> findAll() {
        return (List<ContractAndOption>) contractAndOptionRepository.findAll();
    }
    @Override
    public ContractAndOption save(ContractAndOption contractAndOption) {
        return contractAndOptionRepository.save(contractAndOption);
    }
    @Override
    public Set<ContractAndOption> generateContractAndOptionSet(Contract contract, Set<Option> options) {
        Set<ContractAndOption> result = options.stream()
                .map(option -> new ContractAndOption(contract, option, AVAILABLE))
                .collect(Collectors.toSet());
        return result;
    }
}
