package com.tantan.ToeicWeb.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphRequest {
    private Long idPart;
    private String content;
    private ArrayList<QuestionParagraphRequest> questionParagraphRequests;
    @Nullable
    private MultipartFile image;

    @Nullable
    private MultipartFile audio;
}
