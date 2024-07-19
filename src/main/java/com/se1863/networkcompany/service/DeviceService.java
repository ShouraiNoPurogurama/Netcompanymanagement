package com.se1863.networkcompany.service;

import java.util.List;
import java.util.Set;

import com.se1863.networkcompany.entity.Device;
import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;

public interface DeviceService {
    Device findById(String id);
    Device findByService(Service service);

    List<Device> findAll();
    
    Set<Device> extractDeviceSetFromOptionSet(Set<Option> options);
}
