package com.tantan.ToeicWeb.entity;


import com.tantan.ToeicWeb.entity.history.SelectedList;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String content;

    @NonNull
    private Boolean isTrue;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private List<SelectedList> selectedList;
}
