package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String description;

    @ManyToMany(mappedBy = "topics")
    private Set<Year> years = new HashSet<>();

    @OneToMany(mappedBy = "topic")
    private Set<Test> tests = new HashSet<>();
}
