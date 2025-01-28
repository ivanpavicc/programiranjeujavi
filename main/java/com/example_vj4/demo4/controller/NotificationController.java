package com.example_vj4.demo4.controller;

import com.example_vj4.demo4.model.Notification;
import com.example_vj4.demo4.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List; // Ovo je potrebno za List
import lombok.AllArgsConstructor; // Ovo je potrebno za @AllArgsConstructor

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    // GET /member/{memberId} - Dohvati obavijesti za člana
    @GetMapping("/member/{memberId}")
    public List<Notification> getNotificationsForMember(@PathVariable Long memberId) {
        return notificationService.getNotificationsForMember(memberId);
    }

    // GET /send-test-notification?memberId=1&message=TestMessage - Slanje testne obavijesti putem WebSocket-a
    @GetMapping("/send-test-notification")
    public String sendTestNotification(@RequestParam Long memberId, @RequestParam String message) {
        // Slanje obavijesti putem WebSocket-a na /topic/notifications/{memberId}
        messagingTemplate.convertAndSend("/topic/notifications/" + memberId, message);

        // Povratna poruka koja potvrđuje da je obavijest poslana
        return "Test notification sent to member with ID " + memberId + ": " + message;
    }
}
