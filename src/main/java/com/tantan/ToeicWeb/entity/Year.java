package com.tantan.ToeicWeb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private int year;

    private String description;

    @ManyToMany
    private Set<Topic> topics;

    @OneToMany(mappedBy = "year")
    private Set<Test> tests = new HashSet<>();
}
