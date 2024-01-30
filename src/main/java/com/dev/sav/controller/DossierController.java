package com.dev.sav.controller;

import com.dev.sav.model.Dossier;
import com.dev.sav.model.Technicien;
import com.dev.sav.service.DossierService;
import com.dev.sav.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dossiers")
public class DossierController {

    private final DossierService dossierService;


    @Autowired
    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @GetMapping
    public String getAllDossiers(Model model) {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        model.addAttribute("dossiers", dossiers);
        return "dossiers/dossier";
    }

    @GetMapping("/dossiers-json")
    @ResponseBody
    public List<Dossier> getAllDossiersJson() {
        return dossierService.getAllDossiers();
    }

    @GetMapping("/search")
    public String searchDossierById(@RequestParam("id") int dossierId, Model model) {
        Dossier dossier = dossierService.getDossierById(dossierId);
        if (dossier == null) {
            model.addAttribute("dossierNotFound", true);
        }
        List<Dossier> filteredDossiers = new ArrayList<>();
        filteredDossiers.add(dossier);
        model.addAttribute("dossiers", filteredDossiers);

        return "dossiers/dossier";
    }

    @GetMapping("/{id}")
    public String getDossierById(@PathVariable int id, Model model) {
        Dossier dossier = dossierService.getDossierById(id);
        if (dossier != null) {
            model.addAttribute("dossier", dossier);
            return "dossiers/dossier";
        } else {
            return "error";
        }
    }

    @GetMapping("/add")
    public String showDossierForm(Model model) {
        model.addAttribute("dossier", new Dossier());
        return "dossiers/dossier";
    }

    @PostMapping("/add")
    public String saveDossier(@ModelAttribute Dossier dossier) {
        dossierService.saveDossier(dossier);
        return "redirect:/dossiers";
    }

    @GetMapping("/edit/{id}")
    public String showEditDossierForm(@PathVariable int id, Model model) {
        Dossier dossier = dossierService.getDossierById(id);
        if (dossier != null) {
            model.addAttribute("dossier", dossier);
            return "dossiers/dossier";
        } else {
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateDossier(@PathVariable int id, @ModelAttribute Dossier updatedDossier) {
        dossierService.updateDossier(id, updatedDossier);
        return "redirect:/dossiers";
    }

    @DeleteMapping("/{id}")
    public String deleteDossier(@PathVariable int id) {
        dossierService.deleteDossier(id);
        return "redirect:/dossiers/dossier";
    }

    @PostMapping("/update")
    public String updateDossierByTechnician(@RequestParam("DossierId") int dossierId, @ModelAttribute Dossier updatedDossier) {
        dossierService.updateDossierByTechnicien(dossierId, updatedDossier);
        return "redirect:/techniciens/technicien/" + dossierId;
    }
}
