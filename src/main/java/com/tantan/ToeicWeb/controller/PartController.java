package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Question;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.mapper.ParagraphMapper;
import com.tantan.ToeicWeb.mapper.QuestionMapper;
import com.tantan.ToeicWeb.response.*;
import com.tantan.ToeicWeb.response.question.QuestionNoneAnswerResponse;
import com.tantan.ToeicWeb.services.part.IPartServices;
import com.tantan.ToeicWeb.services.question.IQuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/part")
public class PartController {
    @Autowired
    private IPartServices iPartServices;
    @Autowired
    private IQuestionServices iQuestionServices;

    @GetMapping("/getAll")
    public ResponseEntity<DataResponse> getAllPart() {
        List<PartResponse> partResponseList = iPartServices.getAllPart();
        if (!partResponseList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get all part", partResponseList)
            );
        }
        throw new CustomException(new DataResponse(false, HttpStatus.NO_CONTENT.value(), "No data", null));
    }

    @GetMapping("/getParaByPartId")
    public ResponseEntity<DataResponse> getAllParaByPart(@RequestParam Long idPart) {
        List<Paragraph> paragraphs = iPartServices.getParagraphByPart(idPart);

        ParagraphByPartResponse paragraphByPartResponse = new ParagraphByPartResponse();
        paragraphByPartResponse.setPartId(idPart);
        List<ParagraphResponse> paragraphResponses = new ArrayList<>();
        for (Paragraph paragraph : paragraphs) {
            ParagraphResponse paragraphResponse = ParagraphMapper.INSTANCE.toDTO(paragraph);
            List<Question> questions = iQuestionServices.getQuestionByParagraph(paragraph);
            List<QuestionNoneAnswerResponse> questionList = new ArrayList<>();
            for (Question question : questions) {
                questionList.add(QuestionMapper.INSTANCE.toQuestionResponse(question));
            }
            paragraphResponse.setListQues(questionList);
            paragraphResponses.add(paragraphResponse);
        }
        paragraphByPartResponse.setListPara(paragraphResponses);
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Get all paragraph", paragraphByPartResponse)
        );
    }
}
