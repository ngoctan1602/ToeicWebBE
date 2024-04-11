package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Answer;
import com.tantan.ToeicWeb.response.answer.AnswerResponse;
import com.tantan.ToeicWeb.response.question.QuestionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true,
            value = "select answer.id, answer.content, answer.is_true as isTrue from answer\n" +
                    "join question on answer.question_id = question.id and question.id =?")
    List<AnswerResponse> getAnswerWithIdQuestion(Long idQuestion);
}
