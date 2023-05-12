package com.company.testingsystem.models;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Question {
    private final UUID id;
    private String description;
    private Set<String> variants;
    private String correctAnswer;
    private Subject subject;

    public Question(UUID id, String description, Set<String> variants,
                    String correctAnswer) {
        this.id = id;
        this.description = description;
        this.variants = variants;
        this.correctAnswer = correctAnswer;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getVariants() {
        return variants;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Subject getSubject() {
        return subject;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", variants=" + variants +
                ", subject=" + subject +
                '}';
    }
}
