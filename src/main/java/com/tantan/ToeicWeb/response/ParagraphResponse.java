package com.tantan.ToeicWeb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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
