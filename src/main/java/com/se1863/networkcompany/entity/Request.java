package com.se1863.networkcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.validator.CustomEntityID;

import java.time.LocalDate;
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
public class Request implements IPattern {

    @Id
    @CustomEntityID(idPat = requestIdPat)
    @Column(nullable = false, updatable = false, length = 10, name = "RequestID")
    private String requestId;

    @NonNull
    @Column(name = "Name")
    private String requestName;

    @NonNull
    @Column(nullable = false, length = 20, name = "Status")
    private String status;

    @NonNull
    @Column(name = "RequestDate")
    private LocalDate requestDate;
    
    @NonNull
    @Column(name = "CompletionDate")
    private LocalDate completionDate;

    @Column(columnDefinition = "text", name = "ReasonForCancellation")
    private String reasonForCancellation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClientID")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TechnicianID")
    private Technician technician;

 
}
