package com.email_writer.controller;

import com.email_writer.model.EmailRequest;
import com.email_writer.model.EmailResponse;
import com.email_writer.model.EmailEntity;
import com.email_writer.service.EmailService;
import com.email_writer.repository.EmailRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    // Generate Email
    @PostMapping("/generate")
    public EmailResponse generateEmail(@RequestBody EmailRequest request) {
        return emailService.generateEmail(request);
    }

    // 🔥 HISTORY API (IMPORTANT)
    @GetMapping("/all")
    public List<EmailEntity> getAllEmails() {
        return emailRepository.findAll();
    }

    // Test API
    @GetMapping("/test")
    public String test() {
        return "API Working 🚀";
    }
    @DeleteMapping("/delete/{id}")
    public String deleteEmail(@PathVariable Long id) {
        emailRepository.deleteById(id);
        return "Deleted";
    }
}