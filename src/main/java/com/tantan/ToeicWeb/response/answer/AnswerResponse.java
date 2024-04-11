package com.tantan.ToeicWeb.response.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface AnswerResponse {
    Long getId();

    String getContent();

    Boolean getIsTrue();
}
