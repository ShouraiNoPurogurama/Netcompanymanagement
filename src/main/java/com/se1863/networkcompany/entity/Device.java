package com.se1863.networkcompany.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
public class Device {

    @Id
    @Column(nullable = false, updatable = false, length = 10, name = "DeviceID")
    private String deviceId;

    @NonNull
    @Column(length = 20, name =  "DeviceName")
    private String deviceName;

    @NonNull
    @OneToOne
    @JoinColumn(name = "ServiceID")
    private Service service;

    @ManyToMany(mappedBy = "contractDevices")
    private Set<Contract> deviceContracts;

}
