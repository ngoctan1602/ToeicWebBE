package com.tantan.ToeicWeb.request.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HistoryRequest {
    private String name;
    private Long idTest;
    private List<Long> listIdPart;
    private List<ListChooseRequest> listChoose;
}
