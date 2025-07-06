package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.UtilizatorService;
import com.proiect.awbd.dtos.UtilizatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class DoctorRegisterController {

    private final UtilizatorService utilizatorService;

    @GetMapping("/register-doctor")
    public String showForm(Model model) {
        model.addAttribute("utilizatorDTO", new UtilizatorDTO());
        return "register-doctor";
    }

    @PostMapping("/register-doctor")
    public String register(@ModelAttribute UtilizatorDTO utilizatorDTO, Model model) {
        try {
            utilizatorDTO.setRoluri(Set.of("ROLE_DOCTOR"));
            utilizatorService.save(utilizatorDTO);
            return "redirect:/login?registerSuccess";
        } catch (Exception e) {
            model.addAttribute("error", "Eroare la înregistrare: " + e.getMessage());
            return "register-doctor";
        }
    }
}
