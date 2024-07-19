package com.se1863.networkcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.se1863.networkcompany.validator.PhoneNumber;

import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Technician {

    @Id
    @Column(nullable = false, updatable = false, length = 10 , name = "TechnicianID")
    private String technicianId;

    @NonNull
    @Column(nullable = false, length = 50, name = "FirstName")
    private String firstName;

    @NonNull
    @Column(nullable = false, length = 50, name = "LastName")
    private String lastName;

    @NonNull
    @PhoneNumber
    @Column(nullable = false, length = 15, name = "PhoneNumber")
    private String phoneNumber;
    
    @NonNull
    @Column(nullable = false, length = 50, name = "Email")
    private String email;

    @NonNull
    @Column(nullable = false, length = 40, name = "Role")
    private String role;

    @NonNull
    @Column(nullable = false, length = 100, name = "img")
    private String img;

    @NonNull
    @Column(nullable = false, name = "Status")
    private Integer status;

    @OneToOne(mappedBy = "technician")
    private Account account;

    @OneToMany(mappedBy = "technician")
    private Set<Request> technicianRequests;

}
