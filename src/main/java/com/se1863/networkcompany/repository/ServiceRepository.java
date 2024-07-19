package com.se1863.networkcompany.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.se1863.networkcompany.entity.Service;


public interface ServiceRepository extends JpaRepository<Service, String> {
    Service findByServiceName(String serviceName);

    @Query(value = """
        SELECT COUNT(so.ServiceID) AS NumberOfRegister
        FROM dbo.ServiceOption so
        JOIN dbo.Contract_And_Option co ON co.OptionID = so.OptionID
        WHERE so.ServiceID = :serviceId
        GROUP BY so.ServiceID
            """,nativeQuery = true)
    Integer findNumberOfRegister(@Param("serviceId") String serviceId); 

    @Query(value = """
        SELECT TOP 1 ServiceID
        FROM dbo.Service
        ORDER BY ServiceID DESC
            """, nativeQuery = true)
    String findLastServiceId();

    @Query(value = """
        SELECT *
        FROM dbo.Service
        ORDER BY Status DESC, ServiceID ASC;
            """, nativeQuery = true)
    List<Service> findAllOrderByStatus();
}
