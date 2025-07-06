package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.ClinicaService;
import com.proiect.awbd.dtos.ClinicaDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class ClinicaController {

    private final ClinicaService clinicaService;

    public ClinicaController(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @GetMapping("/clinici")
    public String listClinici(Model model, Authentication authentication) {
        List<ClinicaDTO> clinici = clinicaService.findAll();
        model.addAttribute("clinici", clinici);

        if (authentication != null) {
            model.addAttribute("roles", authentication.getAuthorities());
            System.out.println("ROLURI: " + authentication.getAuthorities());
        }

        return "clinicaList";
    }


    @GetMapping("/clinici/form")
    public String showForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            ClinicaDTO clinica = clinicaService.findById(id);
            model.addAttribute("clinica", clinica);
        } else {
            model.addAttribute("clinica", new ClinicaDTO());
        }
        return "clinicaForm";
    }


    @PostMapping("/clinici/save")
    public String saveClinica(@ModelAttribute ClinicaDTO clinicaDTO) {
        clinicaService.save(clinicaDTO);
        return "redirect:/clinici";
    }

    @GetMapping("clinici/delete/{id}")
    public String deleteClinica(@PathVariable Long id) {
        clinicaService.deleteById(id);
        return "redirect:/clinici";
    }
}
