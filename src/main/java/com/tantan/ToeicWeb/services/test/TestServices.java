package com.tantan.ToeicWeb.services.test;

import com.tantan.ToeicWeb.entity.*;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.repository.*;
import com.tantan.ToeicWeb.request.TestRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import io.micrometer.common.util.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServices implements ITestServices {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private YearRepository yearRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private ParagraphRepository paragraphRepository;

    public final Integer TOTAL_QUESTION = 200;
    public final Integer TOTAL_TIME = 120;

//    @Override
//    public boolean addTestWithTestRequest() {
////        List<Test> test =testRepository.findAll();
////        Test test = testRepository.findById(1L).orElse(null);
////        if (test == null) {
////            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Not found test with id = " + 1, null));
////        }
////        Question question = questionRepository.findById(3L).orElse(null);
////        if (question == null) {
////            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Not found question with id = " + 3, null));
////        }
////        test.getQuestions().add(question);
////        testRepository.save(test);
////        System.out.println(test);
//        return false;
//    }

    @Transactional
    public Test createNewTest(String name, Long idYear, Long idTopic) {
        if (idYear == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Please input idYear", null));
        }
        if (idTopic == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Please input idTopic", null));
        }
        if (StringUtils.isEmpty(name) || StringUtils.isBlank(name)) {
            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Please input name", null));
        }
        Test test = new Test();
        test.setTotalQuestion(TOTAL_QUESTION);
        test.setTotalTime(TOTAL_TIME);
        test.setName(name);
        Year year = yearRepository.findById(idYear).orElse(null);
        if (year == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Not found year with id = " + idYear, null));
        }
        Topic topic = topicRepository.findById(idTopic).orElse(null);
        if (topic == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Not found year with id = " + idYear, null));
        }
        test.setYear(year);
        test.setTopic(topic);
        test.setParts(getAllPart());
        return testRepository.save(test);
    }

    @Transactional
    public List<Part> getAllPart() {
        return partRepository.findAll();
    }

    @Transactional
    public List<Question> addQuestionToTest(List<Long> idQuestion) {
        List<Question> questions = new ArrayList<>();
        for (Long id : idQuestion) {
            Question question = questionRepository.findById(id).orElse(null);
            if (question == null) {
                throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Not found question with id = " + id, null));
            }
            questions.add(question);
        }
        return questions;
    }
    @Transactional
    public List<Paragraph> addParagraphToTest(List<Long> idParagraph) {
        List<Paragraph> paragraphs = new ArrayList<>();
        for (Long id : idParagraph) {
            Paragraph paragraph = paragraphRepository.findById(id).orElse(null);
            if (paragraph == null) {
                throw new CustomException(new DataResponse(true, HttpStatus.SC_NOT_FOUND, "Not found paragraph with id = " + id, null));
            }
            paragraphs.add(paragraph);
        }
        return paragraphs;
    }

    @Transactional
    @Override
    public boolean addTestWithTestRequest(TestRequest testRequest) {
        Test newTest = createNewTest(testRequest.getName(), testRequest.getIdYear(), testRequest.getIdTopic());
        newTest.setQuestions(addQuestionToTest(testRequest.getListIdQuestion()));
        newTest.setParagraphs(addParagraphToTest(testRequest.getListIdParagraph()));
        return false;
    }
}
