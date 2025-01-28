package com.example_vj4.demo4.repository;

import com.example_vj4.demo4.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByMemberId(Long memberId);  // Dohvat obavijesti prema memberId
}
