package com.company.testingsystem.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserTestSolveHistory {

   // private User user;
    private final UUID id;
    private String subject;
    private int score;
    private int max;
    private LocalDateTime time;

    public UserTestSolveHistory(UUID id, String subject,
                                int score, int max) {
        this.id = id;
        this.subject = subject;
        this.score = score;
        this.max = max;
        this.time =LocalDateTime.now();
    }


}
