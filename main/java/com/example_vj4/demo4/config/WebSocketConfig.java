package com.example_vj4.demo4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration  // Ova klasa sadrži konfiguraciju aplikacije
@EnableWebSocketMessageBroker  // Aktivira WebSocket s STOMP protokolom
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registrira STOMP endpoint (kroz koji će se klijenti povezivati)
        registry.addEndpoint("/ws").withSockJS();  // "/ws" je STOMP endpoint
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Definiramo broker za razmjenu poruka
        config.enableSimpleBroker("/topic");  // "/topic" je prefix za kanale poruka
        config.setApplicationDestinationPrefixes("/app");  // "/app" je prefix za zahtjeve od aplikacije
    }
}
