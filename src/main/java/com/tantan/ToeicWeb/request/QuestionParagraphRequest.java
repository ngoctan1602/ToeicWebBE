package com.tantan.ToeicWeb.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionParagraphRequest {
//    private Long idPart;
//    private ParagraphRequest paragraphRequest;
    private String contentQues;
    private String descriptionQues;
    private List<AnswerRequest> answerRequests;
}
