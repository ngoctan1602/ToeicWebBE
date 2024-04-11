package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.*;
import com.tantan.ToeicWeb.response.question.QuestionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByParagraph(Paragraph paragraph);
//    List<Question> findByTestAndPart(Test test, Part part);
    @Query(nativeQuery = true, value ="select question.id as id , audio, content, description, image from question\n" +
            "join test_question on question.id = test_question.question_id\n" +
            "join test on test.id = test_question.test_id\n" +
            "join part on part.id = question.part_id and part.id =? and test.id=?" )
    Set<QuestionDTO> getQuestionByIdTestAndPart(Long idPart, Long idTest);
}
