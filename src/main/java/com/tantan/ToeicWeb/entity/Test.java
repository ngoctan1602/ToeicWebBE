package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;


@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private int totalTime;
    @NonNull
    private int totalQuestion;

    @ManyToOne
    @JoinColumn(name = "year_id")
    private Year year;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToMany(mappedBy = "tests")
    private Set<Part> parts;

    @ManyToMany(mappedBy = "tests")
    private Set<Question> questions;

    @ManyToMany(mappedBy = "tests")
    private Set<Paragraph> paragraphs;
}
