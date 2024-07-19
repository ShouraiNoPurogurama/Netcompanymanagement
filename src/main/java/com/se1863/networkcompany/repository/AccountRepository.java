package com.se1863.networkcompany.repository;

import org.springframework.data.repository.CrudRepository;

import com.se1863.networkcompany.entity.Account;


public interface AccountRepository extends CrudRepository<Account,String> {
    public Account findByUsername(String username);
    public Account findByEmail(String email);
}
