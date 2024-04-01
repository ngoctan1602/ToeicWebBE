package com.tantan.ToeicWeb.services.question;

import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;

public interface IQuestionServices {
    public boolean addNewQuestion(QuestionRequest questionRequest);
    public boolean addNewQuestionWithParagraph(ParagraphRequest paragraphRequest);
}
