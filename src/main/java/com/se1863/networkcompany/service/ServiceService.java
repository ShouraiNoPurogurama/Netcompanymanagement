package com.se1863.networkcompany.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.se1863.networkcompany.entity.Service;
public interface ServiceService {

    void disableService(@RequestParam Map<String, String> reqParams);
    void disableAllService();
    void enableService(@RequestParam Map<String, String> reqParams);
    
    boolean updateServiceName(Service service, String serviceName);
    boolean updateServiceDescription(Service service, String description);
    boolean updateAllOptionStatus(Map<String, String> reqParams);
    boolean checkDuplicatedService(String newServiceName);
    boolean checkAllServiceHasBeenDisabled();
    boolean enableAllService();


    Integer findNumberOfRegister(String serviceId);
    int findAvailableServiceNum();

    Service findById(String id);
    Service findByServiceName(String serviceName);
    Service save(Service service);

    List<Service> findAll();
    List<Service> findAllOrderByStatus();

    Map<String, Integer> findNumberOfRegisters(List<Service> services);
}
