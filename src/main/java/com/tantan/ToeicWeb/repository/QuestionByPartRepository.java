package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.response.question.QuestionByPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionByPartRepository extends JpaRepository<QuestionByPart ,Long> {
//    @Query("select q from question_by_part q where q.partId = ?1")
//    Set<QuestionByPart> findByIdPart(Long idPart);

    @Query(nativeQuery = true,value = "Select * from question_by_part where part_id = ?1")
    List<QuestionByPart> findByPartId(Long PartId);
}
