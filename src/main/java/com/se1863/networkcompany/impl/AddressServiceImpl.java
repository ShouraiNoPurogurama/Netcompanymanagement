package com.se1863.networkcompany.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Address;
import com.se1863.networkcompany.repository.AddressRepository;
import com.se1863.networkcompany.service.AddressService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    
    AddressRepository addressRepository;
    
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
