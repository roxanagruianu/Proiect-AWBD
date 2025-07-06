package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.RolService;
import com.proiect.awbd.Services.UtilizatorService;
import com.proiect.awbd.dtos.RolDTO;
import com.proiect.awbd.dtos.UtilizatorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class UtilizatorController {

    private final UtilizatorService utilizatorService;
    private final RolService rolService;

    @GetMapping("/utilizatori")
    public String listUtilizatori(Model model) {
        log.info("Cerere listare utilizatori");
        model.addAttribute("utilizatori", utilizatorService.findAll());
        return "utilizatoriList";
    }

    @GetMapping("/utilizatori/form")
    public String createForm(Model model) {
        log.info("Cerere creare utilizator nou");
        model.addAttribute("utilizator", new UtilizatorDTO());

        List<String> roluriDisponibile = rolService.findAll().stream()
                .map(RolDTO::getNume)
                .collect(Collectors.toList());

        model.addAttribute("roluriDisponibile", roluriDisponibile);
        return "utilizatoriForm";
    }


    @GetMapping("/utilizatori/form/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        log.info("Cerere editare utilizator cu id: {}", id);
        UtilizatorDTO utilizator = utilizatorService.findById(id)
                .orElseThrow(() -> {
                    log.error("Utilizatorul cu id {} nu a fost găsit", id);
                    return new RuntimeException("Utilizatorul nu a fost găsit");
                });
        model.addAttribute("utilizator", utilizator);

        List<String> roluriDisponibile = rolService.findAll().stream()
                .map(RolDTO::getNume)
                .collect(Collectors.toList());

        model.addAttribute("roluriDisponibile", roluriDisponibile);
        return "utilizatoriForm";
    }

    @PostMapping("/utilizatori/save")
    public String save(@ModelAttribute("utilizator") UtilizatorDTO utilizatorDTO) {
        if (utilizatorDTO.getId() != null) {
            log.info("Actualizare utilizator cu id: {}", utilizatorDTO.getId());
            utilizatorService.update(utilizatorDTO);
        } else {
            log.info("Creare utilizator nou cu username: {}", utilizatorDTO.getUsername());
            utilizatorService.save(utilizatorDTO);
        }
        return "redirect:/utilizatori";
    }


    @GetMapping("/utilizatori/{id}")
    public String viewProfil(@PathVariable Long id, Model model) {
        log.info("Vizualizare profil utilizator cu id: {}", id);
        UtilizatorDTO dto = utilizatorService.findById(id)
                .orElseThrow(() -> {
                    log.error("Utilizatorul cu id {} nu a fost găsit", id);
                    return new RuntimeException("Utilizatorul nu a fost găsit");
                });
        model.addAttribute("utilizator", dto);
        return "utilizatori/profil";
    }

    @GetMapping("/utilizatori/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUtilizator(@PathVariable Long id) {
        log.info("Stergere utilizator cu id: {}", id);
        utilizatorService.deleteById(id);
        return "redirect:/utilizatori";
    }
}

