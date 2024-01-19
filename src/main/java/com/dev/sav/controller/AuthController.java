package com.dev.sav.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dev.sav.dto.UtilisateurDto;
import com.dev.sav.model.Utilisateur;
import com.dev.sav.service.UtilisateurService;

@Controller
public class AuthController {
    private final UtilisateurService utilisateurService;


    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        model.addAttribute("utilisateurDto", utilisateurDto);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("utilisateurDto") UtilisateurDto utilisateurDto,
                               BindingResult result,
                               Model model) {
        Utilisateur existingUser = utilisateurService.findUtilisateurByEmail(utilisateurDto.getEmail());

        if (existingUser != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Il existe déjà un compte enregistré avec la même adresse e-mail");
        }

        if (result.hasErrors()) {
            model.addAttribute("utilisateurDto", utilisateurDto);
            return "register";
        }

        utilisateurService.saveUtilisateur(utilisateurDto);
        return "redirect:/register?success";
    }

    @GetMapping("/utilisateurs")
    public String utilisateurs(Model model) {
        List<UtilisateurDto> utilisateurs = utilisateurService.findAllUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        return "utilisateurs";
    }

}