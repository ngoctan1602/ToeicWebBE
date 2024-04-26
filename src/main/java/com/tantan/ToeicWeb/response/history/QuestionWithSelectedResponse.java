package com.tantan.ToeicWeb.response.history;

public interface QuestionWithSelectedResponse {
    Long getId();
    String getAudio();
    String getContent();
    String getDescription();
    String getImage();
    Long getSelectedQuestion();
    Long getSelectedAnswer();
}
