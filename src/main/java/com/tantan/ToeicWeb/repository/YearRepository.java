package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Topic;
import com.tantan.ToeicWeb.entity.Year;
import com.tantan.ToeicWeb.response.year.YearDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YearRepository extends JpaRepository<Year, Long> {
    @Query(nativeQuery = true, value = "call getYearByTopicId(?)")
    List<YearDTO> getYearByTopicId(Long topicId);
}
