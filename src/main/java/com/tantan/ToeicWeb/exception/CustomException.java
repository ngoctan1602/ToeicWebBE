package com.tantan.ToeicWeb.exception;

import com.tantan.ToeicWeb.response.DataResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class CustomException extends RuntimeException{
    private DataResponse dataResponse;

    public CustomException(DataResponse dataResponse) {
        super(dataResponse.getMessage());
        this.dataResponse = dataResponse;
    }
}
