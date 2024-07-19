package com.se1863.networkcompany.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.validator.CustomEntityID;

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
public class Contract implements IPattern {

    @Id
    @CustomEntityID(idPat = constractIdPat)
    @Column(nullable = false, updatable = false, length = 10, name = "ContractID")
    private String contractId;

    @NonNull
    @Column
    @PastOrPresent(message = "Contract start date must be in the past or Present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractStartDate;

    @NonNull
    @Column
    @FutureOrPresent(message = "Contract start date must be in the future or present.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractEndDate;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClientID")
    private Client client;

    @ManyToMany()
    @JoinTable(name = "Contract_And_Device", joinColumns = @JoinColumn(name = "ContractID"), inverseJoinColumns = @JoinColumn(name = "DeviceID"))
    private Set<Device> contractDevices;

    @OneToMany(mappedBy = "contract")
    private Set<Transaction> contractTransactions;

    @OneToMany(mappedBy = "contract")
    private Set<ContractAndOption> OptionsAndContracts;

    public Contract(String contractId, Date contractStartDate, Date contractEndDate) {
        this.contractId = contractId;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
    }
}
