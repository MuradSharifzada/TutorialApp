package com.tutorial.Tutorial.service;

import com.tutorial.Tutorial.dto.response.ClientResponse;
import com.tutorial.Tutorial.dto.request.LoginClientRequest;
import com.tutorial.Tutorial.dto.request.RegisterClientRequest;

import java.util.List;

public interface ClientService {
    ClientResponse registerClient(RegisterClientRequest request);

    ClientResponse loginClient(LoginClientRequest loginClientRequest);

    List<String> findAllClientEmails();
}
