package com.proiect.awbd.controllers;

import org.springframework.ui.Model;
import com.proiect.awbd.Services.PacientService;
import com.proiect.awbd.dtos.PacientDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pacienti")
public class PacientController {

    private final PacientService pacientService;

    public PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @GetMapping("")
    public String listPacienti(Model model) {
        List<PacientDTO> pacienti = pacientService.findAll();
        if (pacienti == null) {
            pacienti = new ArrayList<>();
        }
        model.addAttribute("pacienti", pacienti);
        return "pacientList";
    }


    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("pacient", new PacientDTO());
        return "pacientForm";
    }

    @PostMapping("/save")
    public String savePacient(@ModelAttribute PacientDTO pacientDTO) {
        pacientService.save(pacientDTO);
        return "redirect:/pacienti";
    }
}
