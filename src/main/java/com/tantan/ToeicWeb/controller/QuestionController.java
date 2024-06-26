package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.request.ParagraphRequest;
import com.tantan.ToeicWeb.request.QuestionByTestRequest;
import com.tantan.ToeicWeb.request.QuestionRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.ParagraphResponse;
import com.tantan.ToeicWeb.response.question.QuestionByPart;
import com.tantan.ToeicWeb.response.question.QuestionWithAnswer;
import com.tantan.ToeicWeb.response.question.QuestionWithAnswerParagraph;
import com.tantan.ToeicWeb.services.paragraph.IParagraphServices;
import com.tantan.ToeicWeb.services.question.IQuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth/question")
public class QuestionController {
    @Autowired
    private IQuestionServices iQuestionServices;
    @Autowired
    private IParagraphServices iParagraphServices;

    @PostMapping("/add")
    public boolean addNewQuestion(@ModelAttribute QuestionRequest questionRequest) {
        return iQuestionServices.addNewQuestion(questionRequest);
    }

    @PostMapping("/add/paragraph")
    public ResponseEntity<DataResponse> addNewQuestionWithParagraph(@ModelAttribute ParagraphRequest paragraphRequest) {
        if (iQuestionServices.addNewQuestionWithParagraph(paragraphRequest)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new DataResponse(false, HttpStatus.CREATED.value(), "Created success", null)
            );
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new DataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error internal server", null)
        );
    }

    @GetMapping("/{idPart}")
    public ResponseEntity<DataResponse> getByIdPart(@PathVariable Long idPart) {
        List<QuestionByPart> questionByParts = iQuestionServices.getQuestionByPart(idPart);
        if (!questionByParts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.CREATED.value(), "Get all successfully", questionByParts)
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new DataResponse(false, HttpStatus.NO_CONTENT.value(), "Not found question by part", null)
        );
    }

    @GetMapping("/getByPartAndType")
    public ResponseEntity<DataResponse> getByIdPartAndIdType(@RequestParam Long idPart, @RequestParam Long idType) {
        List<ParagraphResponse> paragraphResponses = iParagraphServices.getAllQuestionByPartAndType(idPart, idType);
        if (!paragraphResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get all successfully", paragraphResponses)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found question by with Part id is " + idPart + " and Type id is" + idType, null)
        );
    }

    //    @GetMapping("/test/part")
//    public ResponseEntity<DataResponse> getQuestionByTestAndPart(@RequestBody QuestionByTestRequest question) {
//        Set<QuestionWithAnswer> questionWithAnswers = iQuestionServices.getQuestionByTestAndPart(question);
//        if (questionWithAnswers !=null) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new DataResponse(false, HttpStatus.OK.value(), "Get all successfully", questionWithAnswers)
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found question by with Part id is ", null)
//        );
//    }
    @GetMapping("/")
    public ResponseEntity<DataResponse> getQuestionByTestAndPart(@RequestParam Long idTest,
                                                                 @RequestParam Long idPart) {
        List<QuestionWithAnswer> questionWithAnswers = iQuestionServices.getQuestionByTestAndPart(idTest, idPart);
        if (questionWithAnswers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get all successfully", questionWithAnswers)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found question by with Part id is ", null)
        );
    }

    @GetMapping("/paragraph")
    public ResponseEntity<DataResponse>  getQuestionByTestAndPartAndParagraph
            (@RequestParam Long idTest,
             @RequestParam Long idPart) {
        List<QuestionWithAnswerParagraph> questionWithAnswers = iQuestionServices.getQuestionByTestAndPartAndParagraph(idTest, idPart);
        if (!questionWithAnswers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get all successfully", questionWithAnswers)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new DataResponse(false, HttpStatus.NOT_FOUND.value(), "Not found question by with Part id is ", null)
        );
//        return iQuestionServices.getQuestionByTestAndPartAndParagraph(idTest, idPart);
    }
}
