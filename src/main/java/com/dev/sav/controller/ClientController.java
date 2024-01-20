package com.dev.sav.controller;

import com.dev.sav.dto.ClientDto;
import com.dev.sav.model.Client;
import com.dev.sav.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/client")
@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("/registerclient")
    public String showClientRegistrationForm(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "client/registerclient";
    }

    @PostMapping("/registerclient/save")
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

    @GetMapping()
    public String clients(Model model) {
        List<ClientDto> clients = clientService.findAllClients();
        model.addAttribute("clients", clients);
        return "utilisateurs";
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable int id, Model model) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            model.addAttribute("client", client);
            return "client/client";
        } else {
            return "error";
        }
    }


}
