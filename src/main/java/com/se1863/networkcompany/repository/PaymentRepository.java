package com.se1863.networkcompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.se1863.networkcompany.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query(value = """
        SELECT *
        FROM dbo.Payment p 
        JOIN dbo.[TransactionOfContract] t ON t.TransactionID = p.TransactionID
        JOIN dbo.Contract co ON co.ContractID = t.ContractID
        JOIN dbo.Client c ON c.ClientID = co.ClientID
        ORDER BY c.ClientID ASC, p.PaymentDate DESC;
        
                """, nativeQuery = true)
    List<Payment> findAllPaymentOrderByDate();

    @Query(value = """
        SELECT *
        FROM dbo.Payment
        ORDER BY PaymentDate DESC
            """, nativeQuery = true)
    List<Payment> findAllOrderByDate();
}
