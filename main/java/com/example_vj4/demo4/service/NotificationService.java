package com.example_vj4.demo4.service;

import com.example_vj4.demo4.model.Notification;
import com.example_vj4.demo4.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getNotificationsForMember(Long memberId) {
        return notificationRepository.findByMemberId(memberId);  // Dohvat obavijesti prema memberId-u
    }
}
