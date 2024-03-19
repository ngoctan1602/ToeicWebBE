package com.tantan.ToeicWeb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearRequest {
    private Long id;
    private int year;
    private String description;
}
