package com.se1863.networkcompany.service;
import java.util.List;

import com.se1863.networkcompany.entity.Request;
import com.se1863.networkcompany.entity.Technician;
import com.se1863.networkcompany.exception.BlankInputException;
public interface RequestService {
    Request findById(String id);
    Request save(Request request);
    Request confirmRequest(String requestId);
    Request denyRequest(String requestId, String cancellationReason) throws BlankInputException;
    Request saveRequestProcessingStatus(Request request);
    Request assignToTechnician(String requestId, Technician technician);
    Request releaseAllTechnician(String requestId);

    List<Request> findAll();
}
