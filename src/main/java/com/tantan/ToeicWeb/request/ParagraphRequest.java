package com.tantan.ToeicWeb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphRequest {
    private String content;
    private String img;
    private Long idPart;
}
