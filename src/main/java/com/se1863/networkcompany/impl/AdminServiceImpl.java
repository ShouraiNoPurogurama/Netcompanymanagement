package com.se1863.networkcompany.impl;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Admin;
import com.se1863.networkcompany.repository.AdminRepository;
import com.se1863.networkcompany.service.AdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;

    @Override
    public Admin findAdminNameByUsername(String name) {
        return adminRepository.findAdminNameByUsername(name);
    }
}
