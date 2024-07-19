package com.se1863.networkcompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.se1863.networkcompany.entity.Client;
import com.se1863.networkcompany.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, String> {
    List<Contract> findAllByClient(Client client);

    @Query(value = """
            SELECT c.ContractID
            FROM dbo.[TransactionOfContract] t JOIN dbo.Contract c ON t.ContractID = c.ContractID
            WHERE t.Status = '0' AND DATEDIFF(MONTH,t.TransactionDate, GETDATE()) >= 3
                        """, nativeQuery = true)
    List<String> notPaidFor3MonthContractsId();

    @Query(value = """
            SELECT c.ClientID
            FROM dbo.[TransactionOfContract] t JOIN dbo.Contract c ON t.ContractID = c.ContractID
            WHERE t.Status = '0' AND DATEDIFF(MONTH,t.TransactionDate, GETDATE()) >= 3
                        """, nativeQuery = true)
    List<String> notPaidFor3MonthClientId();

    @Query(value = """
            SELECT c.ContractID
            FROM dbo.[TransactionOfContract] t JOIN dbo.Contract c ON t.ContractID = c.ContractID
            WHERE t.Status = '0' AND DATEDIFF(MONTH,t.TransactionDate, GETDATE()) >= 1
                """, nativeQuery = true)
    List<String> notPaidFOrOver1MonthContractId();

    @Query(value = """
        SELECT TOP 1 ContractID
        FROM dbo.Contract
        ORDER BY ContractID DESC
            """, nativeQuery = true)
    String findLastId();

    @Query(value = """
        SELECT 1.1*SUM(so.Price)
        FROM dbo.Contract_And_Option cao
        JOIN dbo.ServiceOption so ON so.OptionID = cao.OptionID
        WHERE ContractID = :contractId
            """, nativeQuery = true)
    Integer findTotalPriceFromContractId(@Param("contractId") String contractId);


}
