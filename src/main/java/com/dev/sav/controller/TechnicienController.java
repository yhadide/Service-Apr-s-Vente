package com.dev.sav.controller;

import com.dev.sav.dto.TechnicienDto;
import com.dev.sav.model.Dossier;
import com.dev.sav.model.Technicien;
import com.dev.sav.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    @GetMapping("technicienslist")
    public String getAllTechniciens(Model model) {
        List<Technicien> techniciens = technicienService.getAllTechniciens();
        model.addAttribute("techniciens", techniciens);
        model.addAttribute("technicienCount", technicienService.getTechnicienCount());
        model.addAttribute("techniciens", techniciens);
        return "techniciens/technicienslist";
    
    }
    @GetMapping("/techniciens-json")
    @ResponseBody
    public List<Technicien> getAllTechniciensJson() {
        List<Technicien> techniciens = technicienService.getAllTechniciens();
        return techniciens;
    }

    @GetMapping("/registertechnicien")
    public String showTechnicienForm(Model model) {
        TechnicienDto technicienDto = new TechnicienDto();
        model.addAttribute("technicienDto", technicienDto);
        return "techniciens/registertechnicien";
    }

    @PostMapping("/registertechnicien/add")
    public String technicienRegistration(@ModelAttribute("technicienDto") TechnicienDto technicienDto,
                                 BindingResult result,
                                 Model model) {
        Technicien existingTechnicien  = technicienService.findByEmail(technicienDto.getEmail());
        if(existingTechnicien != null && !existingTechnicien.getEmail().isEmpty()){
            result.rejectValue("email", null, "Il existe déjà un compte enregistré avec la même adresse e-mail");
        }
        if (result.hasErrors()) {
            model.addAttribute("technicienDto", technicienDto);
            return "techniciens/registertechnicien";
        }
        technicienService.saveTechnicien(technicienDto);
        return "redirect:/techniciens/registertechnicien?success";
    }

    @GetMapping("/{id}")
    public String getTechnicienById(@PathVariable int id, Model model) {
        Technicien technicien = technicienService.getTechnicienById(id);
        if (technicien != null) {
            model.addAttribute("technicien", technicien);
            return "techniciens/technicien";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit")
    public String updateTechnicien(@RequestParam int id, @ModelAttribute TechnicienDto technicienDto) {
        technicienService.updateTechnicien(id, technicienDto);
        return "redirect:/techniciens/" + id;
    }

    @PostMapping("/toggle-status")
    public String toggleTechnicienStatus(@RequestParam int id) {
        technicienService.toggleTechnicienStatus(id);
        return "redirect:/techniciens/technicienslist";
    }
}
