package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.request.TestRequest;
import com.tantan.ToeicWeb.services.test.ITestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/test")
public class TestController {
    @Autowired
    ITestServices iTestServices;

    @PostMapping("/add")
    public boolean getAll(@RequestBody TestRequest testRequest) {
        iTestServices.addTestWithTestRequest(testRequest);
        return true;
    }
}
