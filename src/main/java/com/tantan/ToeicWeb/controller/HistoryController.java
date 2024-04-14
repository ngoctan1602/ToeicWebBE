package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.request.history.HistoryRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.services.history.IHistoryServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/history")
public class HistoryController {
    private final IHistoryServices iHistoryServices;

    public HistoryController(IHistoryServices iHistoryServices) {
        this.iHistoryServices = iHistoryServices;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public  ResponseEntity<DataResponse> createNewHistory(@RequestBody HistoryRequest historyRequest)
    {
        boolean isCreated=  iHistoryServices.createNewHistory(historyRequest);
        if (isCreated)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new DataResponse(false, HttpStatus.CREATED.value(), "History created", null)
            );
        }
        throw new CustomException(new DataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Have error when create history",null));
    }
}
