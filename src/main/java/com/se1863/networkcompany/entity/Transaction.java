package com.se1863.networkcompany.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "TransactionOfContract")
public class Transaction {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( length = 10, name = "TransactionID")
    private String transactionId;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column( name = "TransactionDate")
    private Date transactionDate;

    @NonNull
    @Column(name = "Amount")
    private Integer amount;

    @Column(columnDefinition = "text", name = "Description")
    private String description;

    @NonNull
    @Column(name = "Status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ContractID")
    private Contract contract;

    @OneToMany(mappedBy = "transaction")
    private Set<Payment> transactionPayments;

}
