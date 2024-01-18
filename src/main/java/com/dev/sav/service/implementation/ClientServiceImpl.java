package com.dev.sav.service.implementation;

import com.dev.sav.dto.ClientDto;
import com.dev.sav.model.Client;
import com.dev.sav.model.Role;
import com.dev.sav.repository.ClientRepository;
import com.dev.sav.repository.RoleRepository;
import com.dev.sav.service.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveClient(ClientDto clientDto) {
        Client client = new Client();
        client.setNom(clientDto.getNom());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(clientDto.getAdresse());
        client.setEmail(clientDto.getEmail());
        client.setTelephone(clientDto.getTelephone());
        client.setMotDePasse(passwordEncoder.encode(clientDto.getMotDePasse()));


        Role role = roleRepository.getOrCreateRole("ROLE_CLIENT");
        client.setRoles(Arrays.asList(role));


        clientRepository.save(client);
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public List<ClientDto> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(this::mapToClientDto)
                .collect(Collectors.toList());
    }

    private ClientDto mapToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setNom(client.getNom());
        clientDto.setPrenom(client.getPrenom());
        clientDto.setAdresse(client.getAdresse());
        clientDto.setEmail(client.getEmail());
        clientDto.setTelephone(client.getTelephone());

        return clientDto;
    }
}