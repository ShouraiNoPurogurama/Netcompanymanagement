package com.se1863.networkcompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GuestController {
    @GetMapping("/about")
    public String getMethodName(Model model) {
        return "about";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        return "blog";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/index-home")
    public String indexHome(Model model) {
        return "index-home";
    }

    @GetMapping("/index-text")
    public String indextext(Model model) {
        return "index-text";
    }

    @GetMapping("/onepage-slider")
    public String onepageSlider(Model model) {
        return "onepage-slider";
    }

    @GetMapping("/onepage-text")
    public String onepageText(Model model) {
        return "onepage-text";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model) {
        return "portfolio";
    }

    @GetMapping("/pricing")
    public String pricing(Model model) {
        return "pricing";
    }

    @GetMapping("/service")
    public String service(Model model) {
        return "service";
    }

    @GetMapping("/team")
    public String team(Model model) {
        return "team";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        return "sign-up";
    }

    @GetMapping("/single-post")
    public String singlePost(Model model) {
        return "single-post";
    }
}
