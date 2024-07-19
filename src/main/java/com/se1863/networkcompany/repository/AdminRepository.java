package com.se1863.networkcompany.repository;

import com.se1863.networkcompany.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AdminRepository extends JpaRepository<Admin, String> {
    @Query(value = """
        SELECT *
        FROM dbo.Admin a JOIN dbo.Account acc ON acc.AdminID = a.AdminID
        WHERE acc.Username = ?
            """,nativeQuery = true)
    Admin findAdminNameByUsername(String username);
}
