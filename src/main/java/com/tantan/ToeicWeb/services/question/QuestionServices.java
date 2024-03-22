package com.tantan.ToeicWeb.services.question;

import com.tantan.ToeicWeb.entity.Answer;
import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.mapper.AnswerMapper;
import com.tantan.ToeicWeb.mapper.ParagraphMapper;
import com.tantan.ToeicWeb.mapper.QuestionMapper;
import com.tantan.ToeicWeb.repository.AnswerRepository;
import com.tantan.ToeicWeb.repository.ParagraphRepository;
import com.tantan.ToeicWeb.repository.PartRepository;
import com.tantan.ToeicWeb.repository.QuestionRepository;
import com.tantan.ToeicWeb.request.AnswerRequest;
import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class QuestionServices implements IQuestionServices {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private ParagraphRepository paragraphRepository;

    @Autowired
    private PartRepository partRepository;


    @Override
    @Transactional
    public boolean addNewQuestion(QuestionRequest questionRequest) {

        Long partId =  questionRequest.getParagraphRequest().getIdPart();
        Paragraph paragraph = ParagraphMapper.INSTANCE.toEntity(questionRequest.getParagraphRequest());

//        Paragraph paragraph = new Paragraph();
//        paragraph.setContent(questionRequest.getParagraphRequest().getContent());
//        paragraph.setImg(questionRequest.getParagraphRequest().getImg());
        if (partRepository.existsById(partId)) {
            paragraph.setPart(partRepository.findById(partId).orElse(null));
        }
        Paragraph paragraphCreated = paragraphRepository.save(paragraph);

//        Question question = new Question();
        Question question = QuestionMapper.INSTANCE.toEntity(questionRequest);
        question.setPart(partRepository.findById(partId).orElse(null));
//        question.setContent(questionRequest.getContentQues());
//        question.setDescription(questionRequest.getDescriptionQues());
//        question.setAudio(questionRequest.getAudioQues());
//        question.setImage(questionRequest.getImageQues());
        question.setParagraph(paragraphCreated);
//
//
//
        Question questionCreated = questionRepository.save(question);
        for(AnswerRequest answerRequest: questionRequest.getAnswerRequests())
        {
            Answer answer = new Answer();
            AnswerMapper.INSTANCE.toEntity(answerRequest);
//            answer.setContent(answerRequest.getContent());
//            answer.setTrue(answerRequest.getIsTrue());
            answer.setQuestion(questionCreated);
            answerRepository.save(answer);
        }


//        System.out.println(paragraph);
        return true;
    }
}
