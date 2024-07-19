package com.se1863.networkcompany.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/technician")
public class TechnicianController {

    @GetMapping("/")
    public String defaultPage(Model model,@AuthenticationPrincipal OAuth2User user, Authentication auth) {
        System.out.println(model.getAttribute("user"));
        return "technician/technician-index";
    }

    @GetMapping("/index")
    public String index(Model model,@AuthenticationPrincipal OAuth2User user, Authentication auth) {
        System.out.println("fuck");
        System.out.println(model.getAttribute("user"));
        return "technician/technician-index";
    }

}
