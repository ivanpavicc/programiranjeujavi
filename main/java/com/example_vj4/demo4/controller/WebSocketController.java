package com.example_vj4.demo4.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.DestinationVariable;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    // Konstruktor za injektiranje SimpMessagingTemplate
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Ova metoda mapira poruke poslane na "/app/notify/{memberId}" i šalje ih na "/topic/notifications/{memberId}"
    @MessageMapping("/notify/{memberId}")
    public void sendNotification(String notificationMessage, @DestinationVariable Long memberId) {
        // Slanje poruke na odgovarajući /topic/notifications/{memberId}
        messagingTemplate.convertAndSend("/topic/notifications/" + memberId, notificationMessage);
    }
}
