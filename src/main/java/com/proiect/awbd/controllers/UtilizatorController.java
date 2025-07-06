package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.RolService;
import com.proiect.awbd.Services.UtilizatorService;
import com.proiect.awbd.dtos.RolDTO;
import com.proiect.awbd.dtos.UtilizatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/utilizatori")
@RequiredArgsConstructor
public class UtilizatorController {

    private final UtilizatorService utilizatorService;
    private final RolService rolService;

    @GetMapping
    public String listUtilizatori(Model model) {
        model.addAttribute("utilizatori", utilizatorService.findAll());
        return "utilizatori/lista";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("utilizator", new UtilizatorDTO());

        List<String> roluriDisponibile = rolService.findAll().stream()
                .map(RolDTO::getNume)
                .collect(Collectors.toList());

        model.addAttribute("roluriDisponibile", roluriDisponibile);
        return "utilizatori/form";
    }

    @PostMapping
    public String save(@ModelAttribute("utilizator") UtilizatorDTO utilizatorDTO) {
        utilizatorService.save(utilizatorDTO);
        return "redirect:/utilizatori";
    }

    @GetMapping("/{id}")
    public String viewProfil(@PathVariable Long id, Model model) {
        UtilizatorDTO dto = utilizatorService.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));
        model.addAttribute("utilizator", dto);
        return "utilizatori/profil";
    }
}
