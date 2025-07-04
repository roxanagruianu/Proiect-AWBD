package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.DoctorService;
import com.proiect.awbd.dtos.DoctorDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctori")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("")
    public String listDoctori(Model model) {
        List<DoctorDTO> doctori = doctorService.findAll();
        model.addAttribute("doctori", doctori);
        return "doctorList";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("doctor", new DoctorDTO());
        return "doctorForm";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") DoctorDTO doctorDTO) {
        doctorService.save(doctorDTO);
        return "redirect:/doctori";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctori";
    }
}
