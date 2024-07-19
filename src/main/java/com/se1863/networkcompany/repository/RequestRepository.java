package com.se1863.networkcompany.repository;

import com.se1863.networkcompany.entity.Request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RequestRepository extends JpaRepository<Request, String> {

    @Query(value = """
        SELECT * FROM dbo.Request 
        ORDER BY RequestDate DESC
            """, nativeQuery = true)
    List<Request> findAllOrderByDateDESC();
}
