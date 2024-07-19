package com.se1863.networkcompany.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.se1863.networkcompany.repository.AccountRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    AccountRepository accountRepository;
    BCryptPasswordEncoder passwordEncoder;
    CustomOidcUserService customOidcUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/technician/**").hasAnyAuthority("TECHNICIAN")
                .and()
                .httpBasic()
                .and()
                .formLogin() //form-based login
                .loginPage("/login-page")
                .loginProcessingUrl("/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login-page?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .oauth2Login() //oauth2 login
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.oidcUserService(customOidcUserService)
                )
                .loginPage("/login-page")
                .defaultSuccessUrl("/")
                .failureUrl("/login-page?message=failed")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-page?message=logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring().antMatchers("/resources/static/**");
    }

    @Bean(name = "oidcUserService")
    OAuth2UserService<OidcUserRequest, OidcUser> getOidcUserService() {
        return new CustomOidcUserService();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.builder()
                .username("admin")
                // save the password in encripted form
                .password(passwordEncoder.encode("adminpass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public UserDetailsService getDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}
