package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.TypeParagraphResponse;
import com.tantan.ToeicWeb.services.paragraph.ITypeParagraphServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/type-paragraph")
public class TypeParagraphController {
    @Autowired
    private ITypeParagraphServices iParagraphServices;
    @GetMapping("/getAll")
    public ResponseEntity<DataResponse> getAllPart() {
        List<TypeParagraphResponse> typeParagraphResponses = iParagraphServices.getAllTypeParagraph();

        if (!typeParagraphResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false, HttpStatus.OK.value(), "Get all type paragraph", typeParagraphResponses)
            );
        }
        return  ResponseEntity.status(HttpStatus.OK).body(new DataResponse(false, HttpStatus.NO_CONTENT.value(), "No data", null));
    }
}
