package com.tantan.ToeicWeb.services.year;

import com.tantan.ToeicWeb.request.YearRequest;
import com.tantan.ToeicWeb.response.YearResponse;

import java.util.List;

public interface IYearServices {
    public List<YearResponse> getAllYear();
    public boolean addNewYear(YearRequest yearRequest);
    public YearResponse updateYear(YearRequest yearRequest);
}
