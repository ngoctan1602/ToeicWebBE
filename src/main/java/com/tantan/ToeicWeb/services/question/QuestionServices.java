package com.tantan.ToeicWeb.services.question;

import com.tantan.ToeicWeb.entity.Answer;
import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.exception.CustomException;
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
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.services.image.ICloudServices;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @Autowired
    private ICloudServices iCloudServices;


    @Override
    @Transactional
    public boolean addNewQuestion(QuestionRequest questionRequest) {

        //Create Paragraph
//        Long partId = questionRequest.getParagraphRequest().getIdPart();
//        Paragraph paragraph = ParagraphMapper.INSTANCE.toEntity(questionRequest.getParagraphRequest());
//        if (partRepository.existsById(partId)) {
//            paragraph.setPart(partRepository.findById(partId).orElse(null));
//        }
//        Paragraph paragraphCreated = paragraphRepository.save(paragraph);


        // Lấy thông tin ne
        System.out.println(questionRequest);
        Long idPart = questionRequest.getIdPart();
        Part part = partRepository.findById(idPart).orElse(null);
        if (part == null) {
            throw new CustomException(
                    new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found part", null)
            );
        }
        // Tao question
        Question question = QuestionMapper.INSTANCE.toEntity(questionRequest);
        question.setAudio(iCloudServices.uploadFileImage(questionRequest.getAudioQues(), "video", "video"));
        question.setImage(iCloudServices.uploadFileImage(questionRequest.getImageQues(), "image", "image"));
        question.setPart(part);
        Question questionCreated = questionRepository.save(question);

        // Tao answer

        for (AnswerRequest answerRequest : questionRequest.getAnswerRequests()) {
            Answer answer = AnswerMapper.INSTANCE.toEntity(answerRequest);
          answer.setQuestion(questionCreated);
            System.out.println(answer);
            answerRepository.save(answer);
        }

        return true;
    }
}
