package com.tantan.ToeicWeb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerRequest {
    private String content;
    private Boolean isTrue;
}
