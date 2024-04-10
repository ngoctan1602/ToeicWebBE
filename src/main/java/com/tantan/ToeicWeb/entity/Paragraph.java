package com.tantan.ToeicWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToMany(mappedBy = "paragraphs")
    @JsonIgnore
    private List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "paragraph")
    private Set<Question> questions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeParagraph typeParagraph;
}
