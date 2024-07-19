package com.se1863.networkcompany.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.se1863.networkcompany.entity.Client;


public interface ClientRepository extends CrudRepository<Client, String>{
    Optional<Client> findByPhoneNumber(String phoneNumber);

    @Query(value = """
        SELECT cli.ClientID
        FROM dbo.Client cli JOIN dbo.Contract con ON con.ClientID = cli.ClientID
            JOIN dbo.[TransactionOfContract] tra ON tra.ContractID = con.ContractID
            JOIN dbo.Payment pay ON pay.TransactionID = tra.TransactionID
            WHERE pay.PaymentID = :paymentId
            """, nativeQuery = true)
    String findClientIdByPaymentId(@Param("paymentId") String paymentId);

    @Query(value = """
        SELECT DISTINCT c.ClientID, c.FirstName,c.LastName,c.PhoneNumber,c.Email,c.IsBlocked, t.Status
        FROM dbo.Client c
        LEFT JOIN dbo.Contract con ON con.ClientID = c.ClientID
        LEFT JOIN dbo.[TransactionOfContract] t ON t.ContractID = con.ContractID
        WHERE t.Status = '0'
        UNION
        SELECT DISTINCT c.ClientID, c.FirstName,c.LastName,c.PhoneNumber,c.Email,c.IsBlocked, t.Status
        FROM dbo.Client c
        LEFT JOIN dbo.Contract con ON con.ClientID = c.ClientID
        LEFT JOIN dbo.[TransactionOfContract] t ON t.ContractID = con.ContractID
        WHERE t.Status = '1'
        ORDER BY t.Status DESC, c.ClientID ASC
            """, nativeQuery = true)
    Set<Client> findAllOrderByTransactionStatusDesc();

    @Query(value = """
        SELECT DISTINCT c.ClientID, c.FirstName,c.LastName,c.PhoneNumber,c.Email,c.IsBlocked, t.Status
        FROM dbo.Client c
        LEFT JOIN dbo.Contract con ON con.ClientID = c.ClientID
        LEFT JOIN dbo.[TransactionOfContract] t ON t.ContractID = con.ContractID
        WHERE t.Status = '0'
        UNION
        SELECT DISTINCT c.ClientID, c.FirstName,c.LastName,c.PhoneNumber,c.Email,c.IsBlocked, t.Status
        FROM dbo.Client c
        LEFT JOIN dbo.Contract con ON con.ClientID = c.ClientID
        LEFT JOIN dbo.[TransactionOfContract] t ON t.ContractID = con.ContractID
        WHERE t.Status = '1'
        ORDER BY t.Status ASC, c.ClientID ASC
            """, nativeQuery = true)
    Set<Client> findAllOrderByTransactionStatusAsc();

    @Query(value = """
        SELECT DISTINCT c.ClientID
        FROM dbo.Client c
        LEFT JOIN dbo.Contract con ON con.ClientID = c.ClientID
        LEFT JOIN dbo.[TransactionOfContract] t ON t.ContractID = con.ContractID
        WHERE t.Status = '0' AND DATEDIFF(MONTH, t.TransactionDate, GETDATE()) < 3
            """, nativeQuery = true)
    List<String> notPaidForOver1MonthClients();
}
