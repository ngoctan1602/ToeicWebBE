package com.tantan.ToeicWeb.response;

import com.tantan.ToeicWeb.response.question.QuestionNoneAnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphResponse {
    private Long id;
    private String audio;
    private String img;
    private String content;
    private List<QuestionNoneAnswerResponse> listQues;
}
