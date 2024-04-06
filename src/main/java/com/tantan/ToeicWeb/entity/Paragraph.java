package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paragraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String img;
    private String audio;

    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToMany
    private Set<Test> tests;

    @OneToMany(mappedBy = "paragraph")
    private Set<Question> questions;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeParagraph typeParagraph;
}
