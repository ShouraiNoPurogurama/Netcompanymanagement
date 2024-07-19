package com.se1863.networkcompany.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.se1863.networkcompany.entity.Technician;


public interface TechnicianRepository extends JpaRepository<Technician, String> {
    @Query(value = """
        SELECT *
        FROM dbo.Technician
        WHERE STATUS = 1
            """, nativeQuery = true)
    List<Technician> findAllAvailableTechnicians();
}
