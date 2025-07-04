package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.DiagnosticService;
import com.proiect.awbd.dtos.DiagnosticDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diagnostice")
public class DiagnosticController {

    private final DiagnosticService service;

    public DiagnosticController(DiagnosticService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("diagnostice", service.findAll());
        return "diagnosticList";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("diagnostic", new DiagnosticDTO());
        return "diagnosticForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DiagnosticDTO dto) {
        service.save(dto);
        return "redirect:/diagnostice";
    }
}
