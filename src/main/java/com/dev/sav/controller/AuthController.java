package com.dev.sav.controller;

import com.dev.sav.dto.ClientDto;
import com.dev.sav.dto.UtilisateurDto;
import com.dev.sav.model.Client;
import com.dev.sav.model.Utilisateur;
import com.dev.sav.service.ClientService;
import com.dev.sav.service.UtilisateurService;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {
    private final UtilisateurService utilisateurService;
    private final ClientService clientService;

    public AuthController(UtilisateurService utilisateurService, ClientService clientService) {
        this.utilisateurService = utilisateurService;
        this.clientService = clientService;
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
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

    @GetMapping("/client/registerclient")
    public String showClientRegistrationForm(Model model) {
        ClientDto clientDto = new ClientDto(); // Assuming you have a ClientDto
        model.addAttribute("clientDto", clientDto);
        return "client/registerclient";
    }

    @PostMapping("/client/registerclient/save")
    public String clientRegistration(@ModelAttribute("clientDto") ClientDto clientDto,
                                     BindingResult result,
                                     Model model) {
        Client existingClient = clientService.findClientByEmail(clientDto.getEmail());

        if (existingClient != null && !existingClient.getEmail().isEmpty()) {
            result.rejectValue("email", null, "Il existe déjà un compte enregistré avec la même adresse e-mail");
        }

        if (result.hasErrors()) {
            model.addAttribute("clientDto", clientDto);
            return "client/registerclient";
        }

        clientService.saveClient(clientDto);
        return "redirect:client/registerclient?success";
    }

    @GetMapping("/clients")
    public String clients(Model model) {
        List<ClientDto> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        return "client/client";
    }

}