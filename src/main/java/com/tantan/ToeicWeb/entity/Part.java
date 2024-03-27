package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private int totalQuestion;

    @ManyToMany
    private Set<Test> tests;

    @OneToMany(mappedBy = "part" ,fetch = FetchType.LAZY)
    private Set<Question> questions;

    @OneToMany(mappedBy = "part",fetch = FetchType.LAZY)
    private Set<Paragraph> paragraphs;


}
