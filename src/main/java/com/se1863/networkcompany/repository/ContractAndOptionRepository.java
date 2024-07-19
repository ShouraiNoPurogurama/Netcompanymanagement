package com.se1863.networkcompany.repository;

import org.springframework.data.repository.CrudRepository;

import com.se1863.networkcompany.entity.ContractAndOption;
import com.se1863.networkcompany.entity.ContractAndOptionId;

public interface ContractAndOptionRepository extends CrudRepository<ContractAndOption, ContractAndOptionId>{
    
}
