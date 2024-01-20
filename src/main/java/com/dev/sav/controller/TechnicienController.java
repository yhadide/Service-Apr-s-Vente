package com.dev.sav.controller;

import com.dev.sav.model.Technicien;
import com.dev.sav.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/techniciens")
public class TechnicienController {
    @Autowired
    private TechnicienService technicienService;

    @GetMapping
    public String getAllTechniciens(Model model) {
        List<Technicien> techniciens = technicienService.getAllTechniciens();
        model.addAttribute("techniciens", techniciens);
        return "technicien/list";
    }

    @GetMapping("/{id}")
    public String getTechnicienById(@PathVariable int id, Model model) {
        Technicien technicien = technicienService.getTechnicienById(id);
        if (technicien != null) {
            model.addAttribute("technicien", technicien);
            return "technicien/details";
        } else {
            return "error";
        }
    }

    @GetMapping("/add")
    public String showTechnicienForm(Model model) {
        model.addAttribute("technicien", new Technicien());
        return "technicien/form";
    }

    @PostMapping("/add")
    public String saveTechnicien(@ModelAttribute Technicien technicien) {
        technicienService.saveTechnicien(technicien);
        return "redirect:/techniciens";
    }

    @GetMapping("/edit/{id}")
    public String showEditTechnicienForm(@PathVariable int id, Model model) {
        Technicien technicien = technicienService.getTechnicienById(id);
        if (technicien != null) {
            model.addAttribute("technicien", technicien);
            return "technicien/edit";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTechnicien(@PathVariable int id, @ModelAttribute Technicien updatedTechnicien) {
        technicienService.updateTechnicien(id, updatedTechnicien);
        return "redirect:/techniciens";
    }

    @GetMapping("/delete/{id}")
    public String deleteTechnicien(@PathVariable int id) {
        technicienService.deleteTechnicien(id);
        return "redirect:/techniciens";
    }
}