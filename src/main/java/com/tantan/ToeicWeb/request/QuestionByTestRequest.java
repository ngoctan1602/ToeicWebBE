package com.tantan.ToeicWeb.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionByTestRequest {
    private Long idTest;
    private Long idPart;
}
