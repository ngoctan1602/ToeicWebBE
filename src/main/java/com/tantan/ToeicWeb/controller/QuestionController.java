package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.services.question.IQuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/question")
public class QuestionController {
    @Autowired
    private IQuestionServices iQuestionServices;
    @PostMapping("/add")
    public boolean addNewQuestion (@RequestBody QuestionRequest questionRequest)
    {
       return iQuestionServices.addNewQuestion(questionRequest);
    }
}
