package com.se1863.networkcompany.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.se1863.networkcompany.service.IPattern;
import com.se1863.networkcompany.validator.CustomEntityID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Service implements IPattern {

    @Id
    @CustomEntityID(idPat = serviceIdPat)
    @Column(nullable = false, updatable = false, length = 10, name = "ServiceID")
    private String serviceId;

    @NonNull
    @NotBlank
    @Column(length = 50, name = "ServiceName")
    private String serviceName;

    @Column(columnDefinition = "text", name = "Description")
    private String description;
    
    @NonNull
    @Column(name = "Status")
    private Integer status;

    @OneToOne(mappedBy = "service")
    Device device;

    @OneToMany(mappedBy = "service")
    private Set<Option> serviceOptions;
}
