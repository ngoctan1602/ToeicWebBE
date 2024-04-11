package com.tantan.ToeicWeb.response.question;

import com.tantan.ToeicWeb.response.answer.AnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWithAnswer {
//    private Long id;
//    private String content;
//    private String description;
    private QuestionDTO questionDTO;
    private List<AnswerResponse> answerList;
}
