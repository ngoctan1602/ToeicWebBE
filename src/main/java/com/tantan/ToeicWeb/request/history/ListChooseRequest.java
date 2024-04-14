package com.tantan.ToeicWeb.request.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ListChooseRequest {
    private Long questionId;
    private Long answerId;
}
