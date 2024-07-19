package com.se1863.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.se1863.networkcompany.entity.Device;
import com.se1863.networkcompany.entity.Service;

public interface DeviceRepository extends JpaRepository<Device, String> {
    Device findByService(Service service);
}
