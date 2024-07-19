package com.se1863.networkcompany.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.se1863.networkcompany.entity.Account;
import com.se1863.networkcompany.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    AccountRepository accountRepository;
    
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String email = oidcUser.getAttributes().get("email").toString();
        String name = oidcUser.getAttributes().get("name").toString();
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Account not found");
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
            CustomOidcUser customOidcUser = new CustomOidcUser(oidcUser, email, authorities,name);
            System.out.println(customOidcUser.getAuthorities().toString());
            return customOidcUser;
        }
    }
}