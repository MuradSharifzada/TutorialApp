package com.tutorial.Tutorial.controller;

import com.tutorial.Tutorial.dto.response.ClientResponse;
import com.tutorial.Tutorial.dto.request.LoginClientRequest;
import com.tutorial.Tutorial.dto.request.RegisterClientRequest;
import com.tutorial.Tutorial.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client API", description = "Endpoints for client registration, login, and information retrieval")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    @Operation(
            summary = "Register a new client",
            description = "Registers a new client with the provided details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client registered successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    public ResponseEntity<ClientResponse> registerClient(@Valid @RequestBody RegisterClientRequest request) {
        ClientResponse response = clientService.registerClient(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login a client",
            description = "Authenticates the client with provided login details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client logged in successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized, invalid credentials")
            }
    )
    public ResponseEntity<ClientResponse> loginClient(@Valid @RequestBody LoginClientRequest request) {
        ClientResponse response = clientService.loginClient(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/emails")
    @Operation(
            summary = "Retrieve all client emails",
            description = "Fetches the email addresses of all registered clients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of client emails retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PreAuthorize("hasRole('Author')")
    public ResponseEntity<List<String>> getAllUserEmails() {
        List<String> emails = clientService.findAllClientEmails();
        return ResponseEntity.ok(emails);
    }
}
