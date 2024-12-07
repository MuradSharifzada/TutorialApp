package com.tutorial.Tutorial.dto.response;

import com.tutorial.Tutorial.entity.Client;
import com.tutorial.Tutorial.entity.RoleType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data

public class ClientResponse {
    private Long id;
    private String email;
    private String username;
    private Set<RoleType> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public ClientResponse(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.username = client.getUsername();
        this.roles = client.getRoles();
        this.createdAt = client.getCreatedAt();
        this.updatedAt = client.getUpdatedAt();
    }
}
