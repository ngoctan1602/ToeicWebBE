package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String content;
    private String description;
    private String image;
    private String audio;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToMany
    private Set<Test> tests;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;
}
