package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.request.TopicRequest;
import com.tantan.ToeicWeb.request.YearRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.TopicResponse;
import com.tantan.ToeicWeb.response.YearResponse;
import com.tantan.ToeicWeb.services.topic.ITopicServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/topic")
public class TopicController {
    @Autowired
    private ITopicServices iTopicServices;

    @PostMapping("/add")
    public ResponseEntity<DataResponse> addNewTopic(@RequestBody TopicRequest topicRequest) {
        iTopicServices.addTopic(topicRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new DataResponse(false, HttpStatus.CREATED.value(), "Create year successfully", null)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResponse> getAllTopic() {
        List<TopicResponse> topicResponses=  iTopicServices.getAllTopic();
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Get all topic successfully", topicResponses)
        );
    }
}
