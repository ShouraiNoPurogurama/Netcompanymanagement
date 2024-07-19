package com.se1863.networkcompany.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.se1863.networkcompany.entity.Admin;
import com.se1863.networkcompany.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    AdminService adminService;

    @GetMapping("/")
    public String defaultIndex(Model model,@AuthenticationPrincipal OAuth2User user, Authentication auth) {
        String adminName = "";
        if(user!=null) {
            adminName = (String) user.getAttributes().get("name");
        } else if (auth != null && auth.getName() != null) {
            Admin admin = adminService.findAdminNameByUsername(auth.getName());
            adminName = admin.getFirstName() + " " + admin.getLastName();
        }
        model.addAttribute("user", adminName);
        return "admin/admin-index";
    }

    @GetMapping("/index")
    public String index(Model model,@AuthenticationPrincipal OAuth2User user, Authentication auth) {
        String adminName = "";
        if(user!=null) {
            adminName = (String) user.getAttributes().get("name");
        } else if (auth != null && auth.getName() != null) {
            Admin admin = adminService.findAdminNameByUsername(auth.getName());
            adminName = admin.getFirstName() + " " + admin.getLastName();
        }
        model.addAttribute("user", adminName);
        return "admin/admin-index";
    }

}
