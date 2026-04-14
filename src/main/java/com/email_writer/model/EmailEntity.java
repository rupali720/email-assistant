package com.email_writer.model;

import jakarta.persistence.*;

@Entity
public class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String recipient;
    private String tone;

    @Column(length = 2000)
    private String body;

    private String subject;

    // Getters & Setters

    public Long getId() { return id; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getTone() { return tone; }
    public void setTone(String tone) { this.tone = tone; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
}