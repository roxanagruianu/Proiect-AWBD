package com.proiect.awbd.controllers;

import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Services.ClinicaService;
import com.proiect.awbd.Services.DoctorService;
import com.proiect.awbd.Services.PacientService;
import com.proiect.awbd.Services.ProgramareService;
import com.proiect.awbd.dtos.ProgramareDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ProgramareController {

    private final ProgramareService programareService;
    private final PacientService pacientService;
    private final DoctorService doctorService;
    private final ClinicaService clinicaService;
    private final PacientRepository pacientRepository;
    private final DoctorRepository doctorRepository;

    @GetMapping("/programari")
    public String list(Model model, Authentication authentication) {
        String email = authentication.getName();
        List<ProgramareDTO> programari = List.of();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        boolean isPacient = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_PACIENT"));
        boolean isDoctor = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_DOCTOR"));

        if (isAdmin) {
            programari = programareService.findAll();
        } else if (isPacient) {
            Long pacientId = pacientRepository.findByUsername(email)
                    .orElseThrow(() -> new RuntimeException("Pacientul nu a fost găsit"))
                    .getId();
            programari = programareService.findByPacientId(pacientId);
        } else if (isDoctor) {
            Long doctorId = doctorRepository.findByUsername(email)
                    .orElseThrow(() -> new RuntimeException("Doctorul nu a fost găsit"))
                    .getId();
            programari = programareService.findByDoctorId(doctorId);
        }

        model.addAttribute("programari", programari);
        return "programariList";
    }


    @GetMapping("/programari/form")
    public String form(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("programare", programareService.findById(id));
        } else {
            model.addAttribute("programare", new ProgramareDTO());
        }
        model.addAttribute("pacienti", pacientService.findAll());
        model.addAttribute("doctori", doctorService.findAll());
        model.addAttribute("clinici", clinicaService.findAll());
        return "programareForm";
    }


    @PostMapping("/programari/save")
    public String save(@ModelAttribute ProgramareDTO programareDTO) {
        programareService.save(programareDTO);
        return "redirect:/programari";
    }

    @GetMapping("/programari/delete/{id}")
    public String delete(@PathVariable Long id) {
        programareService.delete(id);
        return "redirect:/programari";
    }
}
