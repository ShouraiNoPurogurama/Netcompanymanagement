package com.se1863.networkcompany.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Account;
import com.se1863.networkcompany.repository.AccountRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Set<GrantedAuthority> authorities = new HashSet<>();
            String userRole = null;
            if (account.getAdmin() != null) {
                userRole = "ADMIN";
            } else if (account.getTechnician() != null) {
                userRole = "TECHNICIAN";
            } else if (account.getClient() != null) {
                userRole = "CLIENT";
            }
            authorities.add(new SimpleGrantedAuthority(userRole));
            CustomUser authUser = new CustomUser(account, authorities);
            return authUser;
        }
    
    }

}
