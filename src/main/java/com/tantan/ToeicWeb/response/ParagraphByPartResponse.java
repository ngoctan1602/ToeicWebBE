package com.tantan.ToeicWeb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphByPartResponse {
    private Long partId;
    private List<ParagraphResponse> listPara;
}
