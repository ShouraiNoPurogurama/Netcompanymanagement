package com.se1863.networkcompany.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
public class Client {

    @Id
    @Column(nullable = false, updatable = false, length = 10 , name = "ClientID")
    private String clientId;

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

    @NonNull
    @Column(name = "IsBlocked")
    private Integer isBlocked;

    @OneToOne(mappedBy = "client")
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Client_And_Address", joinColumns = @JoinColumn(name = "ClientID"), inverseJoinColumns = @JoinColumn(name = "AddressID"))
    private Set<Address> userAddressAddresses;

    //ATTENTION on orphanRemoval, cascade
    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Request> clientRequests;

    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Contract> clientContracts;
    
}
