package com.se1863.networkcompany.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Account {

    @Id
    @Column(name = "Username", nullable = false, updatable = false, length = 20, unique = true)
    private String username;

    @NonNull
    @Column(length = 50, name = "Email")
    private String email;

    @NonNull
    @Column( name = "Password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AdminID",referencedColumnName = "AdminID")
    private Admin admin;

    @OneToOne()
    @JoinColumn(name = "TechnicianID", referencedColumnName = "TechnicianID")
    private Technician technician;

    @OneToOne()
    @JoinColumn(name = "ClientID", referencedColumnName = "ClientID")
    private Client client;

    @Override
    public String toString() {
        return username + ", " + email + ", " + password;
    }
}
