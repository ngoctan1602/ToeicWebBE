package com.tantan.ToeicWeb.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  QuestionRequest {
    private Long idPart;
//    private ParagraphRequest paragraphRequest;
    private String contentQues;
    private String descriptionQues;
    @Nullable
    private MultipartFile audioQues;
    @Nullable
    private MultipartFile imageQues;
    private List<AnswerRequest> answerRequests;
}
