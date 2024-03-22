package com.tantan.ToeicWeb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    private ParagraphRequest paragraphRequest;
    private String contentQues;
    private String descriptionQues;
    private String audioQues;
    private String imageQues;
    private Set<AnswerRequest> answerRequests;


}
