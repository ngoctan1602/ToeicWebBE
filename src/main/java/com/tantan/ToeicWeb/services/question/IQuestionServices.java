package com.tantan.ToeicWeb.services.question;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionByTestRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.response.question.QuestionByPart;
import com.tantan.ToeicWeb.response.question.QuestionWithAnswer;

import java.util.List;
import java.util.Set;

public interface IQuestionServices {
    public boolean addNewQuestion(QuestionRequest questionRequest);

    public boolean addNewQuestionWithParagraph(ParagraphRequest paragraphRequest);

    public List<QuestionByPart> getQuestionByPart(Long idPart);

    public List<Question> getQuestionByParagraph(Paragraph paragraph);

    //    public Set<QuestionWithAnswer> getQuestionByTestAndPart(QuestionByTestRequest question);
    public Set<QuestionWithAnswer> getQuestionByTestAndPart(QuestionByTestRequest question);
    public Set<QuestionWithAnswer> getQuestionByTestAndPart(Long idTest,Long idPart);
}
