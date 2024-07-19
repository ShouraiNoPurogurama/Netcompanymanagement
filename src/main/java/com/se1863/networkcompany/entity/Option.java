package com.se1863.networkcompany.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.se1863.networkcompany.validator.OptionPrice;

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
@Table(name = "ServiceOption")
public class Option {

    @Id
    @Column(nullable = false, updatable = false, length = 10, name = "OptionID")
    private String optionId;

    @NonNull
    @Column(name = "Duration")
    private Integer duration;

    @OptionPrice
    @NonNull
    @Column(name = "Price")
    private Integer price;

    @NonNull
    @Column(name = "Status")
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ServiceID")
    private Service service;

    @OneToMany(mappedBy = "option",fetch = FetchType.EAGER)
    private Set<ContractAndOption> contractsAndOptions;


    @Override
    public String toString() {
        return "" + service.getServiceName() + ", Duration= ";
    }

}
