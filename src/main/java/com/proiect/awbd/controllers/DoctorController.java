package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.DoctorService;
import com.proiect.awbd.dtos.DoctorDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctori")
    public String listDoctori(Model model, Authentication authentication) {
        List<DoctorDTO> doctori = doctorService.findAll();
        model.addAttribute("doctori", doctori);
        if (authentication != null) {
            model.addAttribute("roles", authentication.getAuthorities());
            System.out.println("ROLURI: " + authentication.getAuthorities());
        }
        return "doctorList";
    }

    @GetMapping("/doctori/form")
    public String showForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        DoctorDTO doctor;
        if (id != null) {
            doctor = doctorService.findById(id);
        } else {
            doctor = new DoctorDTO();
        }
        model.addAttribute("doctor", doctor);
        return "doctorForm";
    }


    @PostMapping("/doctori/save")
    public String saveDoctor(@ModelAttribute("doctor") DoctorDTO doctorDTO) {
        doctorService.save(doctorDTO);
        return "redirect:/doctori";
    }

    @GetMapping("/doctori/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctori";
    }
}
