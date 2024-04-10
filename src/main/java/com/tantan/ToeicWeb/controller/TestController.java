package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.request.TestRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.TestResponse;
import com.tantan.ToeicWeb.services.test.ITestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/test")
public class TestController {
    @Autowired
    ITestServices iTestServices;

    @PostMapping("/add")
    public boolean createNew(@RequestBody TestRequest testRequest) {
        boolean b = iTestServices.addTestWithTestRequest(testRequest);
        return true;
    }

    @GetMapping("/topic")
    public ResponseEntity<DataResponse> getTestByTopic(@RequestParam Long idTopic) {
        List<TestResponse> testResponses = iTestServices.getTestByTopic(idTopic);
        if (testResponses.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found test with idTopic = "+idTopic, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Get all test with topic successfully", testResponses)
        );
    }

    @GetMapping("/topic/year")
    public ResponseEntity<DataResponse> getTestByTopicAndYear(@RequestParam Long idTopic,@RequestParam Long idYear) {
        List<TestResponse> testResponses = iTestServices.getTestByTopicAndYear(idTopic,idYear);
        if (testResponses.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found test with idTopic = "+idTopic + " and idYear = "+idYear, null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Get all test with topic and year successfully", testResponses)
        );
    }
}
