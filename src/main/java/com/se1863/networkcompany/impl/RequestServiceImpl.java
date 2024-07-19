package com.se1863.networkcompany.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Request;
import com.se1863.networkcompany.entity.Technician;
import com.se1863.networkcompany.exception.BlankInputException;
import com.se1863.networkcompany.repository.RequestRepository;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.RequestService;
import com.se1863.networkcompany.service.TechnicianService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements IPattern, RequestService {
    RequestRepository requestRepository;
    TechnicianService technicianService;

    @Override
    public List<Request> findAll() {
        return requestRepository.findAllOrderByDateDESC();
    }

    @Override
    public Request findById(String id) {
        return requestRepository.findById(id).get();
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request confirmRequest(String requestId) {
        Request request = findById(requestId);
        request.setStatus(PENDING);
        return save(request);
    }

    @Override
    public Request denyRequest(String requestId, String cancellationReason) throws BlankInputException {
        if (cancellationReason.isBlank())
            throw new BlankInputException("Please enter a cancellation reason.");
        Request request = findById(requestId);
        request.setStatus(CANCELLED);
        request.setReasonForCancellation(cancellationReason);
        return save(request);
    }

    @Override
    public Request saveRequestProcessingStatus(Request request) {
        request.setStatus(PROCESSING);
        return save(request);
    }

    @Override
    public Request assignToTechnician(String requestId, Technician technician) {
        Request request = findById(requestId);
        request.setTechnician(technician);
        save(request);
        technicianService.assignNewRequest(request, technician);
        technicianService.save(technician);
        return request;
    }

    public Request releaseAllTechnician(String requestId) {
        Request request = findById(requestId);
        Technician technician = request.getTechnician();
        technician.setStatus(AVAILABLE);
        technicianService.save(technician);
        request.setTechnician(null);
        return save(request);   
    }
}
