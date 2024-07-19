package com.se1863.networkcompany.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.se1863.networkcompany.service.AdminService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class LoginController {

    InMemoryUserDetailsManager inMemoryUserDetailsManager;
    AdminService adminService;

    @GetMapping("/login-page")
    public String preventLoginAgain(Model model, @AuthenticationPrincipal OAuth2User user, Authentication auth) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String url = "";
        String[] role = { "" };
        if (authentication != null && authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        if (user != null) {
            user.getAuthorities().stream()
                    .forEach(currRole -> {
                        role[0] = currRole.getAuthority();
                    });
        }

        url = switch (role[0]) {
            case "TECHNICIAN" -> "technician/";
            case "ADMIN" -> "admin/";
            default -> "index";
        };
        return "redirect:/" + url;
    }
}
