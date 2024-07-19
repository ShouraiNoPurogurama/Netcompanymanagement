package com.se1863.networkcompany.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Request;
import com.se1863.networkcompany.entity.Technician;
import com.se1863.networkcompany.repository.TechnicianRepository;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.TechnicianService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TechnicianServiceImpl implements IPattern, TechnicianService {
    
    TechnicianRepository technicianRepository;

    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    public Technician findById(String id) {
        return technicianRepository.findById(id).get();
    }

    public Technician save(Technician technician) {
        return technicianRepository.save(technician);
    }

    public List<Technician> findAllAvailableTechnicians() {
        return technicianRepository.findAllAvailableTechnicians();
    }

    public Technician saveStatus(String technicianId) {
        Technician technician = findById(technicianId);
        technician.setStatus(UNAVAILABLE);
        return save(technician);
    }

    public Technician assignNewRequest(Request request, Technician technician) {
        Set<Request> techRequests = technician.getTechnicianRequests();
        if(techRequests == null) techRequests = new HashSet<>();
        techRequests.add(request);
        technician.setTechnicianRequests(techRequests);
        return technician;
    }
}
