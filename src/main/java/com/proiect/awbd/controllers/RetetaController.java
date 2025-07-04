package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.RetetaService;
import com.proiect.awbd.dtos.RetetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/retete")
@RequiredArgsConstructor
public class RetetaController {

    private final RetetaService retetaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("retete", retetaService.findAll());
        return "reteteList";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("reteta", new RetetaDTO());
        return "retetaForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RetetaDTO retetaDTO) {
        retetaService.save(retetaDTO);
        return "redirect:/retete";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        retetaService.deleteById(id);
        return "redirect:/retete";
    }
}
