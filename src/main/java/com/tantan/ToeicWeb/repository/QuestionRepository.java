package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByParagraph(Paragraph paragraph);
    List<Question> findByTestAndPart(Test test, Part part);
}
