package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.ClinicaService;
import com.proiect.awbd.dtos.ClinicaDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clinici")
public class ClinicaController {

    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping
    public String listClinici(Model model) {
        List<ClinicaDTO> clinici = clinicaService.findAll();
        model.addAttribute("clinici", clinici);
        return "clinicaList";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("clinica", new ClinicaDTO());
        return "clinicaForm";
    }

    @PostMapping("/save")
    public String saveClinica(@ModelAttribute ClinicaDTO clinicaDTO) {
        clinicaService.save(clinicaDTO);
        return "redirect:/clinici";
    }

    @GetMapping("/delete/{id}")
    public String deleteClinica(@PathVariable Long id) {
        clinicaService.deleteById(id);
        return "redirect:/clinici";
    }
}
