package com.tantan.ToeicWeb.response.history;

import com.tantan.ToeicWeb.response.PartResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryResponse {
    private Long id;
    private int totalCorrect;
    private Date dateCompleted;
    private Time totalTime;
    private int totalQuestion;
    private String name;
    private List<PartResponse> partResponses;
}
