package com.tantan.ToeicWeb.services.paragraph;

import com.tantan.ToeicWeb.response.ParagraphResponse;

import java.util.List;

public interface IParagraphServices {
    public List<ParagraphResponse> getAllQuestionByPartAndType(Long idPart, Long idType);
}
