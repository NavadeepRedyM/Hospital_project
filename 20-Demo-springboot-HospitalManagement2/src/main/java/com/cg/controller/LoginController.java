package com.cg.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "hospital/login";
    }

    @GetMapping("/redirect")
    public String redirectAfterLogin(Authentication authentication) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        } else if (roles.contains("ROLE_DOCTOR")) {
            return "redirect:/doctor/dashboard";   // ✅ go to controller that sets model
        } else if (roles.contains("ROLE_PATIENT")) {
            return "redirect:/patient/index";
        }
        return "redirect:/login";
    }
}