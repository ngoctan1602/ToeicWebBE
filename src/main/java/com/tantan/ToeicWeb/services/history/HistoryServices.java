package com.tantan.ToeicWeb.services.history;


import com.tantan.ToeicWeb.common.GetIdUser;
import com.tantan.ToeicWeb.entity.*;
import com.tantan.ToeicWeb.entity.history.History;
import com.tantan.ToeicWeb.entity.history.SelectedList;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.mapper.HistoryMapper;
import com.tantan.ToeicWeb.mapper.PartMapper;
import com.tantan.ToeicWeb.repository.*;
import com.tantan.ToeicWeb.request.history.HistoryRequest;
import com.tantan.ToeicWeb.request.history.ListChooseRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.PartResponse;
import com.tantan.ToeicWeb.response.answer.AnswerResponse;
import com.tantan.ToeicWeb.response.history.*;
import com.tantan.ToeicWeb.response.question.QuestionDTO;
import com.tantan.ToeicWeb.response.question.QuestionWithAnswer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServices implements IHistoryServices {
    private final HistoryRepository historyRepository;
    private final SelectedListRepository selectedListRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;
    private final PartRepository partRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public HistoryServices(HistoryRepository historyRepository, SelectedListRepository selectedListRepository, TestRepository testRepository, UserRepository userRepository, PartRepository partRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.historyRepository = historyRepository;
        this.selectedListRepository = selectedListRepository;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
        this.partRepository = partRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    @Override
    public boolean createNewHistory(HistoryRequest historyRequest) {
        History history = createHistoryWithNoListSelected(historyRequest);
        List<SelectedList> selectedLists = new ArrayList<>();
        for (ListChooseRequest listChooseRequest : historyRequest.getListChoose()) {
            Answer answer = answerRepository.findById(listChooseRequest.getAnswerId()).orElseThrow(
                    () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found answer with id=" + listChooseRequest.getAnswerId(), null))
            );
            Question question = questionRepository.findById(listChooseRequest.getQuestionId()).orElseThrow(
                    () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found question with id=" + listChooseRequest.getQuestionId(), null))
            );
            selectedLists.add(SelectedList.builder().answer(answer).history(history).question(question).build());
        }
        for (SelectedList selectedList : selectedLists) {
            selectedListRepository.save(selectedList);
        }
        int totalCorrect = 0;
        for (Part part : history.getParts()) {
            int total = historyRepository.getTotalCorrect(
                    history.getId(),
//                    historyRequest.getIdTest(),
                    new GetIdUser().getId(),
                    part.getId());
            totalCorrect += total;
        }
        History updateHistory = history.toBuilder().totalCorrect(totalCorrect).build();
        historyRepository.save(updateHistory);
        return true;
    }


    @Transactional
    private History createHistoryWithNoListSelected(HistoryRequest historyRequest) {
        Long idTest = historyRequest.getIdTest();
        if (idTest == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Please input test id", null));
        }
        Long idUser = new GetIdUser().getId();
        if (idUser == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Please login", null));
        }
        Test test = testRepository.findById(idTest).orElseThrow(
                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found test with id=" + idTest, null))
        );
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found user with id=" + idUser, null))
        );
        List<Part> parts = new ArrayList<>();
        for (Long idPart : historyRequest.getListIdPart()) {
            Part part = partRepository.findById(idPart).orElseThrow(
                    () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found part with id=" + idPart, null))
            );
            parts.add(part);
        }
        return historyRepository.save(
                History.builder()
                        .totalTime(historyRequest.getTotalTime())
                        .user(user)
                        .test(test)
                        .parts(parts)
                        .dateCompleted(historyRequest.getDateCompleted())
                        .build()
        );
    }

    @Override
    @Transactional
    public List<HistoryResponse> getHistoryWithTestAndUser(Long testId) {

        if (testId == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Please input test id", null));
        }
        Long idUser = new GetIdUser().getId();
        if (idUser == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Please login", null));
        }
        Test test = testRepository.findById(testId).orElseThrow(
                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found test with id=" + testId, null))
        );
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found user with id=" + idUser, null))
        );
        List<History> historyList = historyRepository.findByTestAndUser(test, user);
        List<HistoryResponse> historyResponses = new ArrayList<>();
        for (History history : historyList) {
            HistoryResponse historyResponse = HistoryMapper.INSTANCE.toDTO(history);
            List<PartResponse> partResponses = history.getParts().stream().map(
                    PartMapper.INSTANCE::toDTO
            ).toList();
            historyResponse.setPartResponses(partResponses);
            historyResponses.add(historyResponse);
        }
        return historyResponses;
    }

    @Override
    public List<QuestionWithSelectedAndAnswer> getSelectedListByHistoryAndPart(Long historyId, Long idTest, Long partId) {
//        Long idUser = new GetIdUser().getId();
//        User user = userRepository.findById(idUser).orElseThrow(
//                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found user with id=" + idUser, null))
//        );
        List<QuestionWithSelectedResponse> questions = questionRepository.getQuestionWithSelected(historyId, idTest, partId);
       if(questions.isEmpty())
       {
           throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found selected list", null)) ;
       }
        List<QuestionWithSelectedAndAnswer> questionWithSelectedAndAnswerList = new ArrayList<>();
        for (QuestionWithSelectedResponse question : questions) {
//            List<AnswerResponse> answerResponses = answerRepository.findById(questionDTO.getId())
//                    .stream().map(AnswerMapper.INSTANCE::toAnswerResponse).collect(Collectors.toList());
            List<AnswerResponse> answerResponses = answerRepository.getAnswerWithIdQuestion(question.getId());
            QuestionWithSelectedAndAnswer questionWithAnswer = new QuestionWithSelectedAndAnswer(question, answerResponses);
            questionWithSelectedAndAnswerList.add(questionWithAnswer);
        }
        return questionWithSelectedAndAnswerList;
//        return historyRepository.getSelectedListWithHistoryAndPart(historyId,partId,idUser);

    }

    @Override
    public HistoryResponse getHistoryOverView(Long testId, Long historyId) {
        if (testId == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Please input test id", null));
        }
        Long idUser = new GetIdUser().getId();
        if (idUser == null) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Please login", null));
        }
        Test test = testRepository.findById(testId).orElseThrow(
                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found test with id=" + testId, null))
        );
        User user = userRepository.findById(idUser).orElseThrow(
                () -> new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found user with id=" + idUser, null))
        );
        History history = historyRepository.findById(historyId).orElseThrow(
                () -> new CustomException(
                        new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found history with id " + historyId, null)
                )
        );
        HistoryOverView historyOverView = historyRepository.getHistoryOverview(testId, idUser, historyId);
        if (historyOverView == null) {
            throw new CustomException(
                    new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Not found history with id " + historyId, null)
            );
        }
        HistoryResponse historyResponse = HistoryMapper.INSTANCE.toData(historyOverView);
        if (historyResponse != null) {
            historyResponse.setPartResponses(history.getParts().stream().map(PartMapper.INSTANCE::toDTO).toList());

        }
        return historyResponse;
    }
}
