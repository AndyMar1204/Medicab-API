package com.andy.medicab.services;

public interface EmailService {
    void sendMail(String to, String subject, String text);
}
