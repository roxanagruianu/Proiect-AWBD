package com.proiect.awbd.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeRedirect(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/home";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"))) {
                return "redirect:/doctor/home";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_PACIENT"))) {
                return "redirect:/pacient/home";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model, Authentication authentication) {
        if(authentication != null) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("roles", authentication.getAuthorities());
        }
        return "adminHome";
    }

    @GetMapping("/doctor/home")
    public String doctorHome(Model model, Authentication authentication) {
        if(authentication != null) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("roles", authentication.getAuthorities());
        }
        return "doctorHome";
    }


    @GetMapping("/pacient/home")
    public String pacientHome(Model model, Authentication authentication) {
        if(authentication != null) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("roles", authentication.getAuthorities());
        }
        return "pacientHome";
    }
}
