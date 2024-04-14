package com.tantan.ToeicWeb.entity;

import com.tantan.ToeicWeb.entity.history.History;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
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
    @OneToMany(mappedBy = "test")
    List<History> histories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "year_id")
    private Year year;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "test_part",
            joinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id")
    )
    private List<Part> parts = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "test_question",
            joinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id")
    )
    private List<Question> questions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "test_paragraph",
            joinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "paragraph_id", referencedColumnName = "id")
    )
    private List<Paragraph> paragraphs = new ArrayList<>();
}
