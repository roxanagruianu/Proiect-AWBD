package com.proiect.awbd.controllers;

import com.proiect.awbd.Services.RolService;
import com.proiect.awbd.dtos.RolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/roluri")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @GetMapping
    public String listRoluri(Model model) {
        model.addAttribute("roluri", rolService.findAll());
        return "roluri/lista";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("rol", new RolDTO());
        return "roluri/form";
    }

    @PostMapping
    public String save(@ModelAttribute RolDTO rol) {
        rolService.save(rol);
        return "redirect:/admin/roluri";
    }
}
