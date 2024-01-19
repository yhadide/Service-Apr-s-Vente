package com.dev.sav.controller;

import com.dev.sav.dto.ClientDto;
import com.dev.sav.model.Client;
import com.dev.sav.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/client/registerclient")
    public String showClientRegistrationForm(Model model) {
        ClientDto clientDto = new ClientDto();
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
