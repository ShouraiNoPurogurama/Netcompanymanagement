package com.se1863.networkcompany.repository;

import com.se1863.networkcompany.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, String> {
}
