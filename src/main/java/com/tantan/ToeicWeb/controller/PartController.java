package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.PartResponse;
import com.tantan.ToeicWeb.services.part.IPartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/part")
public class PartController {
    @Autowired
    private IPartServices iPartServices;

    @GetMapping("/getAll")
    public ResponseEntity<DataResponse> getAllPart()
    {
        List<PartResponse> partResponseList = iPartServices.getAllPart();
        if(!partResponseList.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new DataResponse(false,HttpStatus.OK.value(), "Get all part",partResponseList)
            );
        }
        throw new CustomException(new DataResponse(false,HttpStatus.NO_CONTENT.value(), "No data",null));
    }
}
