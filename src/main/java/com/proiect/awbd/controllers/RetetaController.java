package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.RetetaService;
import com.proiect.awbd.dtos.RetetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class RetetaController {

    private final RetetaService retetaService;

    @GetMapping("/retete")
    public String list(Model model, Authentication authentication) {
        List<RetetaDTO> retete = retetaService.findAll();
        model.addAttribute("retete", retete);
        if (authentication != null) {
            model.addAttribute("roles", authentication.getAuthorities());
            System.out.println("ROLURI: " + authentication.getAuthorities());
        }
        return "reteteList";
    }

    @GetMapping("/retete/form")
    public String form(Model model) {
        model.addAttribute("reteta", new RetetaDTO());
        return "retetaForm";
    }

    @PostMapping("/retete/save")
    public String save(@ModelAttribute RetetaDTO retetaDTO) {
        retetaService.save(retetaDTO);
        return "redirect:/retete";
    }

    @GetMapping("/retete/delete/{id}")
    public String delete(@PathVariable Long id) {
        retetaService.deleteById(id);
        return "redirect:/retete";
    }
}
