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
public class TypeParagraph {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "typeParagraph")
    private Set<Paragraph> paragraphs;

}
