package com.se1863.networkcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.se1863.networkcompany.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query(value = """
                    SELECT t.TransactionID, t.ContractID,t.TransactionType,t.TransactionDate,t.Amount,t.Description,t.Status
            FROM dbo.[TransactionOfContract] t
            INNER JOIN dbo.Contract c ON t.ContractID = c.ContractID
            INNER JOIN dbo.Client cli ON c.ClientID = cli.ClientID
            WHERE cli.ClientID = :clientId
            """, nativeQuery = true)
    Transaction findByclientId(@Param("clientId") String clientId);

    @Query(value = """
        SELECT TOP 1 TransactionID
        FROM dbo.[TransactionOfContract]
        ORDER BY TransactionID DESC
            """, nativeQuery = true)
    String findLastTransactionId();
}
