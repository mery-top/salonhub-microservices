package com.meerthika.service;

import com.meerthika.modal.Notification;
import com.meerthika.payload.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {

    NotificationDTO createNotification(Notification notification);
    List<Notification> getAllNotificationByUserId(Long userId);
    List<Notification> getAllNotificationBySalonId(Long salonId);
    Notification markNotificationAsRead(Long notificationId);
}
