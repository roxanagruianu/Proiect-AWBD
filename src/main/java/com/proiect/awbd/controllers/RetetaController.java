package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.RetetaService;
import com.proiect.awbd.dtos.RetetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class RetetaController {

    private final RetetaService retetaService;

    @GetMapping("/retete")
    public String list(Model model, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isDoctor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));
        boolean isPacient = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_PACIENT"));

        List<RetetaDTO> retete;

        if (isAdmin) {
            retete = retetaService.findAll();
        } else if (isDoctor) {
            String username = authentication.getName();
            retete = retetaService.findByDoctorUsername(username);
        } else if (isPacient) {
            String username = authentication.getName();
            retete = retetaService.findByPacientUsername(username);
        } else {
            retete = List.of();
        }

        model.addAttribute("retete", retete);
        return "reteteList";
    }


    @GetMapping("/retete/form")
    @PreAuthorize("hasRole('DOCTOR')")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id != null) {
            RetetaDTO reteta = retetaService.findById(id).orElse(new RetetaDTO());
            model.addAttribute("reteta", reteta);
        } else {
            model.addAttribute("reteta", new RetetaDTO());
        }
        return "retetaForm";
    }

    @PostMapping("/retete/save")
    public String save(@ModelAttribute RetetaDTO retetaDTO) {
        retetaService.save(retetaDTO);
        return "redirect:/retete";
    }

    @GetMapping("/retete/delete/{id}")
    public String delete(@PathVariable Long id) {
        retetaService.deleteById(id);
        return "redirect:/retete";
    }
}
