package com.se1863.networkcompany.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.se1863.networkcompany.entity.Device;
import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;
import com.se1863.networkcompany.repository.DeviceRepository;
import com.se1863.networkcompany.service.DeviceService;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    DeviceRepository deviceRepository;

    @Override
    public Device findById(String id) {
        return deviceRepository.findById(id).get();
    }

    @Override
    public Device findByService(Service service) {
        return deviceRepository.findByService(service);
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Set<Device> extractDeviceSetFromOptionSet(Set<Option> options) {
        Set<Device> result = options.stream()
                .map(option -> findByService(option.getService()))
                .collect(Collectors.toSet());
        return result;
    }
}
