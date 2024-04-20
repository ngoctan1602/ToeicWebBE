package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.entity.Topic;
import com.tantan.ToeicWeb.entity.Year;
import com.tantan.ToeicWeb.response.test.NameTestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Long> {
    List<Test> findByTopicAndYear(Topic topic, Year year);
    List<Test> findByTopic(Topic topic);
    @Query(nativeQuery = true, value = "select name from test where id =?")
    NameTestDTO getNameTestById(Long idTest);
}
