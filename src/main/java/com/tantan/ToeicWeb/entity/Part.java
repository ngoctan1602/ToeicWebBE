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
@Getter
@Setter
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

    @ManyToMany(mappedBy = "parts")
    @JsonIgnore
    private List<Test> tests = new ArrayList<>();

    @OneToMany(mappedBy = "part" ,fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "part",fetch = FetchType.LAZY)
    private Set<Paragraph> paragraphs = new HashSet<>();


}
