package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.request.history.HistoryRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.history.HistoryResponse;
import com.tantan.ToeicWeb.response.history.QuestionWithSelectedAndAnswer;
import com.tantan.ToeicWeb.response.history.QuestionWithSelectedResponse;
import com.tantan.ToeicWeb.response.history.SelectedListResponse;
import com.tantan.ToeicWeb.services.history.IHistoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/history")
public class HistoryController {
    private final IHistoryServices iHistoryServices;

    public HistoryController(IHistoryServices iHistoryServices) {
        this.iHistoryServices = iHistoryServices;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<DataResponse> createNewHistory(@RequestBody HistoryRequest historyRequest) {
        boolean isCreated = iHistoryServices.createNewHistory(historyRequest);
        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new DataResponse(false, HttpStatus.CREATED.value(), "History created", null)
            );
        }
        throw new CustomException(new DataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Have error when create history", null));
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<DataResponse> getHistoryWithTestAndUser(@RequestParam Long idTest) {
        List<HistoryResponse> history = iHistoryServices.getHistoryWithTestAndUser(idTest);
        if (!history.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get history with test and user", history)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Not found history ", null)
        );
    }

    @GetMapping("/part")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<DataResponse> getSelectedListByHistoryAndPart(@RequestParam Long idHistory,
                                                                        @RequestParam Long idTest,
                                                                        @RequestParam Long idPart) {
        List<QuestionWithSelectedAndAnswer> selectedListResponses = iHistoryServices.getSelectedListByHistoryAndPart(idHistory, idTest,idPart);
        if (!selectedListResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get selected list in historyId = " + idHistory + " and partId = " + idPart, selectedListResponses)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Not found selected list ", null)
        );
    }

    @GetMapping("/overview")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<DataResponse> getOverView(@RequestParam Long idHistory,
                                                    @RequestParam Long idTest) {
        HistoryResponse selectedListResponses = iHistoryServices.getHistoryOverView(idTest, idHistory);
        if (selectedListResponses!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get overview in historyId = " + idHistory + " and partId = " + idTest, selectedListResponses)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.OK.value(), "Not found history ", null)
        );
    }

}
