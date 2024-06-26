package com.tantan.ToeicWeb.request.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HistoryRequest {
    private Date dateCompleted;
    private Time totalTime;
    private Long idTest;
    private int totalQuestion;
    private List<Long> listIdPart;
    private List<ListChooseRequest> listChoose;
}
