package com.dev.sav.service;

import com.dev.sav.dto.ClientDto;
import com.dev.sav.model.Client;

import java.util.List;

    public interface ClientService {
        void saveClient(ClientDto clientDto);
        Client getClientById(int noClient);
        Client findClientByEmail(String email);
        void updateClient(int clientId, ClientDto clientDto);
        List<ClientDto> findAllClients();

    }
