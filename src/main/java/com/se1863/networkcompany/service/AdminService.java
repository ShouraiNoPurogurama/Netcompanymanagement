package com.se1863.networkcompany.service;

import com.se1863.networkcompany.entity.Admin;

public interface AdminService {
    Admin findAdminNameByUsername(String name);
}
