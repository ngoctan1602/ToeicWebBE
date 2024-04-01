package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.services.question.IQuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/question")
public class QuestionController {
    @Autowired
    private IQuestionServices iQuestionServices;
    @PostMapping("/add")
    public boolean addNewQuestion (@ModelAttribute QuestionRequest questionRequest)
    {
       return iQuestionServices.addNewQuestion(questionRequest);
    }
    @PostMapping("/add/paragraph")
    public ResponseEntity<DataResponse> addNewQuestionWithParagraph (@ModelAttribute ParagraphRequest paragraphRequest)
    {
        if(iQuestionServices.addNewQuestionWithParagraph(paragraphRequest))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new DataResponse(false,HttpStatus.CREATED.value(), "Created success",null)
            );
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new DataResponse(true,HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error internal server",null)
        );
    }
}
