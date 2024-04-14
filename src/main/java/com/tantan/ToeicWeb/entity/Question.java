package com.tantan.ToeicWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tantan.ToeicWeb.entity.history.SelectedList;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToMany(mappedBy = "questions")
    @JsonIgnore
    private List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Paragraph paragraph;

    @OneToMany(mappedBy = "question")
    private List<SelectedList> selectedList;
}
