package com.tantan.ToeicWeb.response.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionNoneAnswerResponse {
    private Long id;
    private String content;
}
