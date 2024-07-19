package com.se1863.networkcompany.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomOidcUser implements OidcUser {

    private final OidcUser oidcUser;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private String name;

    @Override
    public Map<String, Object> getClaims() {
        return oidcUser.getClaims();
    }
    @Override
    public OidcUserInfo getUserInfo() {
        return oidcUser.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return oidcUser.getIdToken();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oidcUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
        // return oidcUser.getAuthorities(); vai lon coi chung dong nay
    }

    @Override
    public String getName() {
        return oidcUser.getName();
    }

}