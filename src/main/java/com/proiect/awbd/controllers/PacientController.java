package com.proiect.awbd.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.proiect.awbd.Services.PacientService;
import com.proiect.awbd.dtos.PacientDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping()
public class PacientController {

    private final PacientService pacientService;

    public PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @GetMapping("/pacienti")
    public String listPacienti(Model model, Authentication authentication) {
        List<PacientDTO> pacienti;

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isDoctor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));

        if (isAdmin) {
            pacienti = pacientService.findAll();
        } else if (isDoctor) {
            String username = authentication.getName();
            pacienti = pacientService.findPacientiByDoctorUsername(username);
        } else {
            pacienti = List.of();
        }

        model.addAttribute("pacienti", pacienti);
        model.addAttribute("isAdmin", isAdmin);
        return "pacientList";
    }

    @GetMapping("/pacienti/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        PacientDTO pacient;
        if (id != null) {
            pacient = pacientService.findById(id);
            if (pacient == null) {
                throw new RuntimeException("Pacientul nu a fost găsit");
            }
        } else {
            pacient = new PacientDTO();
        }

        model.addAttribute("pacient", pacient);
        return "pacientForm";
    }

    @PostMapping("/pacienti/save")
    public String savePacient(@ModelAttribute PacientDTO pacientDTO) {
        pacientService.save(pacientDTO);
        return "redirect:/pacienti";
    }
}
