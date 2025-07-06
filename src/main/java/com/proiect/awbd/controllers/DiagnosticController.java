package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.DiagnosticService;
import com.proiect.awbd.dtos.DiagnosticDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class DiagnosticController {

    private final DiagnosticService service;

    public DiagnosticController(DiagnosticService service) {
        this.service = service;
    }

    @GetMapping("/diagnostice")
    public String list(Model model, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        boolean isDoctor = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));
        boolean isPacient = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_PACIENT"));

        List<DiagnosticDTO> diagnostice;

        String username = authentication.getName();

        if (isAdmin) {
            diagnostice = service.findAll();
        } else if (isDoctor) {
            diagnostice = service.findByDoctorUsername(username);
        } else if (isPacient) {
            diagnostice = service.findByPacientUsername(username);
        } else {
            diagnostice = List.of();
        }

        model.addAttribute("diagnostice", diagnostice);
        return "diagnosticList";
    }

    @GetMapping("/diagnostice/form")
    @PreAuthorize("hasRole('DOCTOR')")
    public String form(@RequestParam(required = false) Long id, Model model) {
        DiagnosticDTO diagnostic;

        if (id != null) {
            diagnostic = service.findById(id)
                    .orElseThrow(() -> new RuntimeException("Diagnosticul nu a fost găsit"));
        } else {
            diagnostic = new DiagnosticDTO();
        }

        model.addAttribute("diagnostic", diagnostic);
        return "diagnosticForm";
    }


    @PostMapping("/diagnostice/save")
    @PreAuthorize("hasRole('DOCTOR')")
    public String save(@ModelAttribute DiagnosticDTO dto) {
        service.save(dto);
        return "redirect:/diagnostice";
    }
}
