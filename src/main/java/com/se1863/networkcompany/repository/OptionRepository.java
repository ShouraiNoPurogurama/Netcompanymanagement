package com.se1863.networkcompany.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.se1863.networkcompany.entity.Option;

public interface OptionRepository extends CrudRepository<Option, String> {

    @Query(value = "SELECT * " +
            "FROM dbo.[ServiceOption] o " +
            "INNER JOIN dbo.Contract_And_Option cao ON cao.OptionID = o.OptionID " +
            "WHERE cao.ContractID = :contractId ", nativeQuery = true)
    Set<Option> findBycontractId(@Param("contractId") String contractId);

    @Query(value = """
            SELECT *
            FROM dbo.ServiceOption
            WHERE ServiceID = :serviceId
            ORDER BY Duration
                """, nativeQuery = true)
    List<Option> findAllOptionByServiceId(@Param("serviceId") String serviceId);

    @Query(value = """
            SELECT TOP 1 OptionID
            FROM dbo.ServiceOption
            ORDER BY OptionID DESC
                """, nativeQuery = true)
    String findLastOptionId();

    @Query(value = """
        SELECT * FROM dbo.ServiceOption
        WHERE Duration = :duration AND ServiceID = :serviceId AND Status != -1
            """,nativeQuery = true)
    Option findByDurationAndServiceId(@Param("duration") Integer duration, @Param("serviceId") String serviceId);

}
