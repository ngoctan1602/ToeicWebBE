package com.tantan.ToeicWeb.services.year;

import com.tantan.ToeicWeb.entity.Topic;
import com.tantan.ToeicWeb.entity.Year;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.repository.TopicRepository;
import com.tantan.ToeicWeb.repository.YearRepository;
import com.tantan.ToeicWeb.request.YearRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.YearResponse;
import com.tantan.ToeicWeb.response.year.YearDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YearServices implements IYearServices {
    @Autowired
    private YearRepository yearRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<YearResponse> getAllYear() {

        return yearRepository.findAll().stream().map(
                year -> new YearResponse(
                        year.getId(),
                        year.getYear(),
                        year.getDescription()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public List<YearResponse> getYearByIdPart(Long id) {
        return null;
    }

    @Override
    public boolean addNewYear(YearRequest yearRequest) {
        Year year = new Year();
        year.setYear(yearRequest.getYear());
        year.setDescription(yearRequest.getDescription());
        try {
            yearRepository.save(year);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    @Override
    public YearResponse updateYear(YearRequest yearRequest) {
        Year year = yearRepository.findById(yearRequest.getId()).orElseThrow(
                () -> {
                    DataResponse dataResponse = new DataResponse(false, 404, "Không tìm thấy năm với id: " + yearRequest.getId(), null);
                    return new CustomException(dataResponse);
                }
//        () -> new NoSuchElementException("Không tìm thấy năm với id: " + yearRequest.getId())
        );
        year.setDescription(yearRequest.getDescription());
        yearRepository.save(year);
        return new YearResponse(year.getId(), year.getYear(), year.getDescription());
    }

    @Override
    public List<YearDTO> getYearByTopic(Long idTopic) {
        Topic topic = topicRepository.findById(idTopic).orElseThrow(
                () -> new CustomException(new DataResponse(
                        true, HttpStatus.NOT_FOUND.value(),
                        "Not found topic with id = " + idTopic,
                        null
                ))
        );
        return yearRepository.getYearByTopicId(idTopic);
    }
}
