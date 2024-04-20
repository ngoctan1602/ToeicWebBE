package com.tantan.ToeicWeb.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestResponse {
    private Long idTest;
    private int totalQuestion;
    private int totalTime;
    private String name;
    private int totalPart;
    private List<PartResponse> partResponse;
}
