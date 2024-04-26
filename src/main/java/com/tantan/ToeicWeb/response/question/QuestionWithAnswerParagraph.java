package com.tantan.ToeicWeb.response.question;

import com.tantan.ToeicWeb.response.answer.AnswerResponse;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWithAnswerParagraph {
    private Long id;
    private String content;
    private String audio;
    private String img;
   private List<QuestionWithAnswer> questionWithAnswerList;
}
