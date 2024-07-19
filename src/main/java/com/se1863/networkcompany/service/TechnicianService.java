package com.se1863.networkcompany.service;

import java.util.List;

import com.se1863.networkcompany.entity.Request;
import com.se1863.networkcompany.entity.Technician;

public interface TechnicianService {
    
    Technician findById(String id);
    Technician save(Technician technician);
    Technician saveStatus(String technicianId);
    Technician assignNewRequest(Request request, Technician technician);

    List<Technician> findAll();
    List<Technician> findAllAvailableTechnicians();

}
