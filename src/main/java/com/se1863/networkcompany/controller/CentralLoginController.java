package com.se1863.networkcompany.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.se1863.networkcompany.entity.Admin;
import com.se1863.networkcompany.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
// @SessionAttributes("user")
@AllArgsConstructor
public class CentralLoginController {

    AdminService adminService;

    // dang nhap xong duoc redirect vao day
    @GetMapping("/")
    public String centralController(Model model, Authentication auth, @AuthenticationPrincipal OAuth2User user,
            HttpSession session) {
        String url = "index";
        String name = "";
        String[] role = { "" };
        int[] counter = { 0 };
        
        if (user == null && auth == null)
            return "index";

        if (user != null) {
            name = (String) user.getAttributes().get("name");
            user.getAuthorities().stream()
                    .forEach(currRole -> {
                        role[counter[0]++] = currRole.getAuthority();
                    });
            url = switch (role[0]) {
                case "TECHNICIAN" -> "technician/";
                case "ADMIN" -> "admin/";
                default -> "/";
            };
            if (url.equals("/")) {
                session.setAttribute("user", name);
                return "index";
            }
            session.setAttribute("user", name);
        } else if (auth != null) {
            Admin admin = adminService.findAdminNameByUsername(auth.getName());
            name = (String) admin.getFirstName() + " " + admin.getLastName();
            auth.getAuthorities().stream()
                    .forEach(currRole -> {
                        role[counter[0]++] = currRole.getAuthority();
                    });
            url = switch (role[0]) {
                case "TECHNICIAN" -> "technician/";
                case "ADMIN" -> "admin/";
                default -> "index";
            };
            session.setAttribute("user", name);
        }
        return "redirect:" + url;
    }

    @GetMapping("/index")
    public String index(Model model, Authentication auth, @AuthenticationPrincipal OAuth2User user,
            HttpSession session) {
        String url = "index";
        String name = "";
        String[] role = { "" };
        int[] counter = { 0 };
        if (user == null && auth == null)
            return "index";

        if (user != null) {
            name = (String) user.getAttributes().get("name");
            user.getAuthorities().stream()
                    .forEach(currRole -> {
                        role[counter[0]++] = currRole.getAuthority();
                    });
            url = switch (role[0]) {
                case "TECHNICIAN" -> "technician/";
                case "ADMIN" -> "admin/";
                default -> "/";
            };
            session.setAttribute("user", name);
        } else if (auth != null) {
            Admin admin = adminService.findAdminNameByUsername(auth.getName());
            name = admin.getFirstName() + " " + admin.getLastName();
            auth.getAuthorities().stream()
                    .forEach(currRole -> {
                        role[counter[0]++] = currRole.getAuthority();
                    });
            url = switch (role[0]) {
                case "TECHNICIAN" -> "technician/";
                case "ADMIN" -> "admin/";
                default -> "/";
            };
            session.setAttribute("user", name);
        }
        return "redirect:" + url;
    }
}
