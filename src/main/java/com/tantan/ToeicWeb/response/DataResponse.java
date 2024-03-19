package com.tantan.ToeicWeb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse {
    private boolean error;
    private int statusCode;
    private String message;
    private Object data;
}
