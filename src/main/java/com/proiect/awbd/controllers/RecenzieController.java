package com.proiect.awbd.controllers;

import com.proiect.awbd.Repositories.DoctorRepository;
import com.proiect.awbd.Repositories.PacientRepository;
import com.proiect.awbd.Services.RecenzieService;
import com.proiect.awbd.Services.DoctorService;
import com.proiect.awbd.Services.PacientService;
import com.proiect.awbd.dtos.RecenzieDTO;
import com.proiect.awbd.dtos.DoctorDTO;
import com.proiect.awbd.dtos.PacientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class RecenzieController {

    private final RecenzieService recenzieService;
    private final DoctorService doctorService;
    private final PacientService pacientService;
    private final PacientRepository pacientRepository;
    private final DoctorRepository doctorRepository;

    @GetMapping("/recenzii")
    public String list(Model model, Authentication authentication) {
        String email = authentication.getName();
        List<RecenzieDTO> recenzii = List.of();

        boolean isPacient = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_PACIENT"));
        boolean isDoctor = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_DOCTOR"));

        if (isPacient) {
            Long pacientId = pacientRepository.findByUsername(email)
                    .orElseThrow(() -> new RuntimeException("Pacientul nu a fost găsit"))
                    .getId();
            recenzii = recenzieService.findByPacientId(pacientId);
        } else if (isDoctor) {
            Long doctorId = doctorRepository.findByUsername(email)
                    .orElseThrow(() -> new RuntimeException("Doctorul nu a fost găsit"))
                    .getId();
            recenzii = recenzieService.findByDoctorId(doctorId);
        }

        model.addAttribute("recenzii", recenzii);
        return "recenzieList";
    }

    @GetMapping("/recenzii/form")
    public String form(Model model) {
        model.addAttribute("recenzie", new RecenzieDTO());
        model.addAttribute("doctori", doctorService.findAll());
        model.addAttribute("pacienti", pacientService.findAll());
        return "recenzieForm";
    }

    @PostMapping("/recenzii/save")
    public String save(@ModelAttribute RecenzieDTO recenzieDTO) {
        recenzieDTO.setDataRecenzie(LocalDateTime.now());
        recenzieService.save(recenzieDTO);
        return "redirect:/recenzii";
    }

    @GetMapping("/recenzii/delete/{id}")
    public String delete(@PathVariable Long id) {
        recenzieService.deleteById(id);
        return "redirect:/recenzii";
    }

    @GetMapping("/recenzii/doctor/{doctorId}")
    public String recenziiDoctor(@PathVariable Long doctorId, Model model) {
        List<RecenzieDTO> recenzii = recenzieService.findByDoctorId(doctorId);
        model.addAttribute("recenzii", recenzii);
        return "recenzieListDoctori";
    }
}
