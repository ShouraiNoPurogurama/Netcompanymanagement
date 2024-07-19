package com.se1863.networkcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Address {

    @Id
    @Column(nullable = false, updatable = false, length = 10, name = "AddressID")
    private String addressId;

    @NonNull
    @Column(length = 50)
    private String city;

    @NonNull
    @Column(length = 50)
    private String addressLine;

    @NonNull
    @Column
    private Integer streetNumber;

    @NonNull
    @Column(length = 20)
    private String unitNumber;

    @ManyToMany(mappedBy = "userAddressAddresses")
    private Set<Client> userAddressClients;

}
