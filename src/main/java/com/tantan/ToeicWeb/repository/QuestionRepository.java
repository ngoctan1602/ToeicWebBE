package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.*;
import com.tantan.ToeicWeb.response.history.QuestionWithSelectedResponse;
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
    List<QuestionDTO> getQuestionByIdTestAndPart(Long idPart, Long idTest);

    @Query(
            nativeQuery = true,
            value = "select question.id as id , question.audio, question.content, question.description, question.image from question\n" +
                    "join part on part.id = question.part_id and part.id =?\n" +
                    "join paragraph on paragraph.id = question.paragraph_id and paragraph.id = ?\n" +
                    "join test_question on question.id = test_question.question_id\n" +
                    "join test on test.id = test_question.test_id and test.id =?\n"
    )
    List<QuestionDTO> getQuestionByIdTestAndIdPartAndIdParagraph(Long idPart, Long idParagraph,Long idTest);
    @Query(
            nativeQuery = true,
            value = "call getQuestionWithSelected(?, ?, ?);"
    )
    List<QuestionWithSelectedResponse> getQuestionWithSelected(Long idHistory, Long idTest, Long idPart);

}
