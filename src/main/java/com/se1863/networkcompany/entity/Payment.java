package com.se1863.networkcompany.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Payment {

    @Id
    @Column(length = 10, name = "PaymentID")
    private String paymentId;

    @NonNull
    @Column(precision = 12, scale = 2, name = "Amount")
    private Integer amount;

    @NonNull
    @Column(name = "PaymentDate")
    private Date paymentDate;

    @NonNull
    @Column(name = "Status")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TransactionID")
    private Transaction transaction;

}
