package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.ProgramareService;
import com.proiect.awbd.dtos.ProgramareDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/programari")
@RequiredArgsConstructor
public class ProgramareController {

    private final ProgramareService programareService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("programari", programareService.findAll());
        return "programariList";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("programare", new ProgramareDTO());
        return "programareForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ProgramareDTO programareDTO) {
        programareService.save(programareDTO);
        return "redirect:/programari";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        programareService.delete(id);
        return "redirect:/programari";
    }
}
