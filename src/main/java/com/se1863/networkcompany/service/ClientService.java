package com.se1863.networkcompany.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.exception.ObjectNotFoundException;

public interface ClientService {
    void disableAllClientRequests(String clientId);

    Client findByPhoneNumber(String searchInput);
    Client save(Client unwrappedClient);
    Client findById(String clientID) throws ObjectNotFoundException;
    Client blockAndSave(Client client);
    Client unblockAndSave(Client client);
    Client extractClientFromReqParams(Map<String, String> reqParams);

    Set<Client> findAllOrderByTransactionStatusDesc();
    Set<Client> findAllOrderByTransactionStatusAsc();

    List<Client> findAll();
    List<String> notPaidForOver1MonthClients();
}
