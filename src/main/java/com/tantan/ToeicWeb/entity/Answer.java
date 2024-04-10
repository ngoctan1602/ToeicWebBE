package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String content;

    @NonNull
    private Boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
