package com.tantan.ToeicWeb.services.question;

import com.tantan.ToeicWeb.entity.*;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.mapper.AnswerMapper;
import com.tantan.ToeicWeb.mapper.QuestionMapper;
import com.tantan.ToeicWeb.repository.*;
import com.tantan.ToeicWeb.request.AnswerRequest;
import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.QuestionByPart;
import com.tantan.ToeicWeb.services.image.ICloudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private TypeParagraphRepository typeParagraphRepository;

    @Autowired
    private QuestionByPartRepository questionByPartRepository;

    @Override
    @Transactional
    public boolean addNewQuestion(QuestionRequest questionRequest) {

        // Tạo câu hỏi thuộc part lẻ như 1, 2, 5
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
        String urlAudio = iCloudServices.uploadFileImage(questionRequest.getAudioQues(), "video", "video");
        String urlImage = iCloudServices.uploadFileImage(questionRequest.getImageQues(), "image", "image");
        if (urlAudio != null) {
            question.setAudio(urlAudio);
        }
        if (urlImage != null) {
            question.setImage(urlImage);
        }
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

    @Override
    @Transactional
    public boolean addNewQuestionWithParagraph(ParagraphRequest paragraphRequest) {
//        System.out.println(paragraphRequest);

        // Thêm mới paragraph
        Long partId = paragraphRequest.getIdPart();
        Part part = partRepository.findById(partId).orElse(null);

        if (part == null) {
            throw new CustomException(new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found part", null));
        }
        Long typeId = paragraphRequest.getIdType();
        TypeParagraph typeParagraph = typeParagraphRepository.findById(typeId).orElse(null);
        Paragraph paragraph = new Paragraph();
        if (typeParagraph != null) {
            paragraph.setTypeParagraph(typeParagraph);
        }
        paragraph.setPart(part);
        paragraph.setContent(paragraphRequest.getContent());
        String urlAudio = iCloudServices.uploadFileImage(paragraphRequest.getAudio(), "video", "video");
        String urlImage = iCloudServices.uploadFileImage(paragraphRequest.getImage(), "image", "image");
        if (urlAudio != null) {
            paragraph.setAudio(urlAudio);
        }
        if (urlImage != null) {
            paragraph.setImg(urlImage);
        }

        Paragraph newPara = paragraphRepository.save(paragraph);

        //Thêm danh sách câu hỏi
        for (QuestionParagraphRequest questionRequest : paragraphRequest.getQuestionParagraphRequests()) {
            Question question = new Question();
            question.setPart(part);
            question.setParagraph(newPara);
            question.setContent(questionRequest.getContentQues());
            question.setDescription(questionRequest.getDescriptionQues());
            Question newQues = questionRepository.save(question);
            for (AnswerRequest answerRequest : questionRequest.getAnswerRequests()) {
                Answer answer = AnswerMapper.INSTANCE.toEntity(answerRequest);
                answer.setQuestion(newQues);
//                System.out.println(answer);
                answerRepository.save(answer);
            }
        }
        return true;
    }

    @Override
    public List<QuestionByPart> getQuestionByPart(Long idPart) {
        List<QuestionByPart> questionByPartSet = questionByPartRepository.findByPartId(idPart);
        return questionByPartSet;
    }

    @Override
    public List<Question> getQuestionByParagraph(Paragraph paragraph) {
        List<Question> questions = questionRepository.findByParagraph(paragraph);
        System.out.println(questions);
        return questions;
    }
}
