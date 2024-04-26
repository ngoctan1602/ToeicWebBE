package com.tantan.ToeicWeb.response.history;

import com.tantan.ToeicWeb.response.answer.AnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionWithSelectedAndAnswer {
    private QuestionWithSelectedResponse question;
    private List<AnswerResponse> answer;
}
