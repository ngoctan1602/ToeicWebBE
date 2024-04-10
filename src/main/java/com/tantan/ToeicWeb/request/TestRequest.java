package com.tantan.ToeicWeb.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestRequest {
    private Long idYear;
    private Long idTopic;
    private String name;
    private List<Long> listIdQuestion;
    private List<Long> listIdParagraph;
}
