package com.tantan.ToeicWeb.services.question;

import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.response.QuestionByPart;

import java.util.ArrayList;
import java.util.List;

public interface IQuestionServices {
    public boolean addNewQuestion(QuestionRequest questionRequest);
    public boolean addNewQuestionWithParagraph(ParagraphRequest paragraphRequest);
    public List<QuestionByPart> getQuestionByPart(Long idPart);
}
