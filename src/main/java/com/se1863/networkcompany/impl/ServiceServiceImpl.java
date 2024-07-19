package com.se1863.networkcompany.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestParam;

import com.se1863.networkcompany.entity.Option;
import com.se1863.networkcompany.entity.Service;
import com.se1863.networkcompany.exception.DuplicateInputException;
import com.se1863.networkcompany.repository.ServiceRepository;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.OptionService;
import com.se1863.networkcompany.service.ServiceService;

import lombok.AllArgsConstructor;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceServiceImpl implements IPattern, ServiceService {
    ServiceRepository serviceRepository;
    OptionService optionService;

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public List<Service> findAllOrderByStatus() {
        return serviceRepository.findAllOrderByStatus();
    }

    @Override
    public Service findById(String id) {
        return serviceRepository.findById(id).get();
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public Integer findNumberOfRegister(String serviceId) {
        Integer registerNumber = serviceRepository.findNumberOfRegister(serviceId);
        return registerNumber != null ? registerNumber : 0;
    }

    @Override
    public Map<String, Integer> findNumberOfRegisters(List<Service> services) {
        Map<String, Integer> result = services.stream()
                .map(service -> service.getServiceId())
                .collect(Collectors.toMap(entry -> entry, entry -> findNumberOfRegister(entry)));
        return result;
    }

    @Override
    public boolean updateServiceName(Service service, String serviceName) {
        if (serviceName.isBlank() || service.getServiceName().equals(serviceName.trim()))
            return false;
        service.setServiceName(serviceName);
        return true;
    }

    @Override
    public boolean updateServiceDescription(Service service, String description) {
        if (description.isBlank() || service.getDescription().equals(description.trim()))
            return false;
        service.setDescription(description);
        return true;
    }

    @Override
    public boolean updateAllOptionStatus(Map<String, String> reqParams) {
        Map<String, String> optionStatusMap = optionService.extractOptionStatusFromReqAtt(reqParams);
        boolean[] statusChanged = { false }; // use arr to be able to set its value in stream
        List<Option> options = new ArrayList<>();
        optionStatusMap.keySet().stream()
                .forEach(key -> {
                    System.out.println(key);
                    Option option = optionService.findById(key);
                    options.add(option);
                });
        options.stream()
                .forEach(option -> {
                    int currStatus = option.getStatus();
                    option.setStatus(optionStatusMap.get(option.getOptionId()).equals("enable") ? 1 : 0);
                    System.out.println(option.getStatus());
                    optionService.save(option);
                    statusChanged[0] = (currStatus != option.getStatus() && !statusChanged[0]);
                });
        return statusChanged[0];
    }

    @Override
    public void disableService(@RequestParam Map<String, String> reqParams) {
        reqParams.keySet().stream()
                .forEach(serviceId -> {
                    Service service = findById(serviceId);
                    service.setStatus(DISABLED);
                    save(service);
                });
    }

    @Override
    public void disableAllService() {
        List<Service> services = findAll();
        services.stream()
                .forEach(service -> {
                    service.setStatus(DISABLED);
                    save(service);
                });
    }

    @Override
    public void enableService(@RequestParam Map<String, String> reqParams) {
        reqParams.keySet().stream()
                .forEach(serviceId -> {
                    Service service = findById(serviceId);
                    service.setStatus(AVAILABLE);
                    save(service);
                });
    }

    @Override
    public Service findByServiceName(String serviceName) {
        return serviceRepository.findByServiceName(serviceName);
    }

    public boolean checkDuplicatedService(String newServiceName) throws DuplicateInputException {
        if(findByServiceName(newServiceName) != null) throw new DuplicateInputException("The service "+newServiceName + " has already been existed.");
        return false;
    }

    public boolean checkAllServiceHasBeenDisabled() {
        List<Service> services = findAll();
        boolean disabledAll = services.stream()
            .anyMatch(service -> service.getStatus() == AVAILABLE);
        return !disabledAll;
    }

    public boolean enableAllService() {
        List<Service> services = findAll();
        services.stream()
            .forEach(service -> {
                service.setStatus(AVAILABLE);
                save(service);
            });
        return true;
    }

    public int findAvailableServiceNum() {
        List<Service> services = findAll();
        int result = (int) services.stream()
            .filter(service -> service.getStatus() == 1)
            .count();
        return result;
    }
} 
