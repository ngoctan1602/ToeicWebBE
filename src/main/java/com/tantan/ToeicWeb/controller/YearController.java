package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.request.YearRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.YearResponse;
import com.tantan.ToeicWeb.services.year.IYearServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/auth/year")
@RestController
public class YearController {
    @Autowired
    private IYearServices iYearServices;
    @GetMapping("/getAll")
    public ResponseEntity<DataResponse> getAllYear()
    {
       List<YearResponse> yearResponses= iYearServices.getAllYear();
       if(!yearResponses.isEmpty())
       {
           return ResponseEntity.status(HttpStatus.OK).body(
                   new DataResponse(false,HttpStatus.OK.value(), "Get all year successfully",yearResponses)
           );
       }
       throw new CustomException(new DataResponse(true,HttpStatus.NOT_FOUND.value(), "Not found year",null));
    }
    @PostMapping("/add")
    public ResponseEntity<DataResponse> addNewYear(@RequestBody YearRequest yearRequest)
    {
        if(iYearServices.addNewYear(yearRequest))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new DataResponse(false,HttpStatus.CREATED.value(), "Create year successfully",null)
            );
        }
        throw new RuntimeException("Error in server");
    }

    @PutMapping("/update")
    public ResponseEntity<DataResponse> updateUser(@RequestBody YearRequest yearRequest)
    {
        YearResponse yearResponse = iYearServices.updateYear(yearRequest);
        if(yearResponse !=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new DataResponse(false,HttpStatus.CREATED.value(), "Update year successfully",yearResponse)
            );
        }
        throw new RuntimeException("Error in server");
    }
}
