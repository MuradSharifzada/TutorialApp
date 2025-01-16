package com.tutorial.Tutorial.client;

import com.tutorial.Tutorial.dto.request.NotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8081/api", name = "Notification-service")
public interface NotificationClient {

    @PostMapping("/notifications/send")
    @PreAuthorize("hasRole('Author')")
    ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request);
}
