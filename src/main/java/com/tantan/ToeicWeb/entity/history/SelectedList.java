package com.tantan.ToeicWeb.entity.history;

import com.tantan.ToeicWeb.entity.Answer;
import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "selected_list")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SelectedList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    private History history;
}
