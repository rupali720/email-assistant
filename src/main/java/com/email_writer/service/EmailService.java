package com.email_writer.service;

import com.email_writer.model.EmailRequest;
import com.email_writer.model.EmailResponse;
import com.email_writer.model.EmailEntity;
import com.email_writer.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    private Random random = new Random();

    public EmailResponse generateEmail(EmailRequest request) {

        String subject = "Regarding " + request.getTopic();
        String date = LocalDate.now().toString();

        // 🔥 Random openings
        String[] formalOpenings = {
                "I hope this message finds you well.",
                "I hope you are doing well.",
                "Greetings, I trust you are in good health."
        };

        String[] informalOpenings = {
                "Hope you're doing great 😊",
                "Hey! Hope everything is good.",
                "Hi there! How's it going?"
        };

        // 🔥 Random endings
        String[] formalEndings = {
                "Thank you for your time and consideration.",
                "I would appreciate your response.",
                "Looking forward to your reply."
        };

        String[] informalEndings = {
                "Let me know what you think!",
                "Catch you later!",
                "Waiting for your reply 😄"
        };

        String body;

        if ("formal".equalsIgnoreCase(request.getTone())) {

            String opening = formalOpenings[random.nextInt(formalOpenings.length)];
            String ending = formalEndings[random.nextInt(formalEndings.length)];

            body = "Date: " + date + "\n\n"
                    + "Dear " + request.getRecipient() + ",\n\n"
                    + opening + "\n\n"
                    + "I am writing to formally discuss " + request.getTopic() + ".\n\n"
                    + ending + "\n\n"
                    + "Regards.";

        } else {

            String opening = informalOpenings[random.nextInt(informalOpenings.length)];
            String ending = informalEndings[random.nextInt(informalEndings.length)];

            body = "Date: " + date + "\n\n"
                    + "Hi " + request.getRecipient() + ",\n\n"
                    + opening + "\n\n"
                    + "Just wanted to talk about " + request.getTopic() + ".\n\n"
                    + ending;
        }

        // Save to DB
        EmailEntity email = new EmailEntity();
        email.setTopic(request.getTopic());
        email.setRecipient(request.getRecipient());
        email.setTone(request.getTone());
        email.setSubject(subject);
        email.setBody(body);

        emailRepository.save(email);

        return new EmailResponse(subject, body);
    }
}