package com.tantan.ToeicWeb.services.test;

import com.tantan.ToeicWeb.request.TestRequest;
import com.tantan.ToeicWeb.response.TestResponse;
import com.tantan.ToeicWeb.response.test.NameTestDTO;

import java.util.List;

public interface ITestServices {
    public boolean addTestWithTestRequest(TestRequest testRequest);
    public List<TestResponse> getTestByTopic(Long idTopic);
    public List<TestResponse> getTestByTopicAndYear(Long idTopic, Long idYear);
    public TestResponse getTestById(Long idTest);
    public NameTestDTO getNameTestById(Long idTest);

}
