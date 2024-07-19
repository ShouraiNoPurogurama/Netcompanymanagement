package com.se1863.networkcompany.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.exception.BlankInputException;
import com.se1863.networkcompany.exception.ObjectNotFoundException;
import com.se1863.networkcompany.repository.ClientRepository;
import com.se1863.networkcompany.service.ClientService;
import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.service.RequestService;
import com.se1863.networkcompany.service.TechnicianService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService, IPattern {

    RequestService requestService;
    TechnicianService technicianService;

    public static Client unwrapClient(Optional<Client> client, String id) {
        if (client.isPresent())
            return client.get();
        else
            throw new ObjectNotFoundException(id);
    }

    ClientRepository clientRepository;

    @Override
    public Client findByPhoneNumber(String searchInput) {
        return clientRepository.findByPhoneNumber(searchInput).get();
    }

    @Override
    public Client save(Client unwrappedClient) {
        return clientRepository.save(unwrappedClient);
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Client findById(String clientID) throws ObjectNotFoundException {
        return unwrapClient(clientRepository.findById(clientID), clientID);
    }

    @Override
    public Client blockAndSave(Client client) {
        client.setIsBlocked(1);
        return save(client);
    }

    @Override
    public Client unblockAndSave(Client client) {
        client.setIsBlocked(0);
        return save(client);
    }

    @Override
    public Client extractClientFromReqParams(Map<String, String> reqParams) {
        String clientId = reqParams.get("clientId");
        if (clientId == null)
            throw new BlankInputException("Please enter client id.");
        Client client = findById(clientId);
        if (client == null)
            throw new ObjectNotFoundException(clientId);
        return client;
    }

    @Override
    public Set<Client> findAllOrderByTransactionStatusDesc() {
        return clientRepository.findAllOrderByTransactionStatusDesc();
    }

    @Override
    public Set<Client> findAllOrderByTransactionStatusAsc() {
        return clientRepository.findAllOrderByTransactionStatusAsc();
    }

    @Override
    public List<String> notPaidForOver1MonthClients() {
        return clientRepository.notPaidForOver1MonthClients();
    }

    public void disableAllClientRequests(String clientId) {
        Client client = findById(clientId);
        client.getClientRequests().stream()
                .filter(request -> !request.getStatus().equals(COMPLETED))
                .forEach(request -> {
                    if (request.getStatus().equals(PROCESSING)) requestService.releaseAllTechnician(request.getRequestId());
                    request.setStatus(CANCELLED);
                    request.setReasonForCancellation("Client account has been disabled by Admin");
                    requestService.save(request);
                });
        save(client);
    }
}
