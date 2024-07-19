package com.se1863.networkcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.se1863.networkcompany.validator.PhoneNumber;

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
public class Admin {

    @Id
    @Column(nullable = false, updatable = false, length = 10, name = "AdminID")
    private String adminId;

    @NonNull
    @Column(name = "FirstName", nullable = false, length = 50)
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

    @OneToOne(mappedBy = "admin") //name of the mapped field in Account class
    private Account account;

}
