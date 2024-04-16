package com.tantan.ToeicWeb.services.year;

import com.tantan.ToeicWeb.request.YearRequest;
import com.tantan.ToeicWeb.response.YearResponse;
import com.tantan.ToeicWeb.response.year.YearDTO;

import java.util.List;

public interface IYearServices {
    public List<YearResponse> getAllYear();
    public List<YearResponse> getYearByIdPart(Long id);
    public boolean addNewYear(YearRequest yearRequest);
    public YearResponse updateYear(YearRequest yearRequest);
    public List<YearDTO> getYearByTopic(Long idTopic);
}
