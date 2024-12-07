package com.tutorial.Tutorial.service.impl;

import com.tutorial.Tutorial.dto.response.ClientResponse;
import com.tutorial.Tutorial.dto.request.LoginClientRequest;
import com.tutorial.Tutorial.dto.request.RegisterClientRequest;
import com.tutorial.Tutorial.entity.Client;
import com.tutorial.Tutorial.entity.RoleType;
import com.tutorial.Tutorial.exception.ResourceAlreadyExistException;
import com.tutorial.Tutorial.exception.ResourceNotFoundException;
import com.tutorial.Tutorial.repository.ClientRepository;
import com.tutorial.Tutorial.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public ClientResponse registerClient(RegisterClientRequest request) {

        log.info("Starting registration process for email: {}", request.getEmail());

        if (clientRepository.existsByEmail(request.getEmail())) {
            log.warn("Registration failed - email already in use: {}", request.getEmail());
            throw new ResourceAlreadyExistException("Email is already in use");
        }
        Client client = new Client();
        client.setEmail(request.getEmail());
        client.setUsername(request.getUsername());
        client.setPassword(passwordEncoder.encode(request.getPassword()));
        client.setCreatedAt(request.getCreatedAt());
        client.setRoles(Set.of(RoleType.USER));

        Client savedClient = clientRepository.save(client);

        return new ClientResponse(savedClient);
    }

    @Override
    public ClientResponse loginClient(LoginClientRequest request) {
        Client client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), client.getPassword())) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        return new ClientResponse(client);
    }


    public List<String> findAllClientEmails() {
        log.info("Searching email for  all clients ");
        return clientRepository.findAllEmails();
    }
}

