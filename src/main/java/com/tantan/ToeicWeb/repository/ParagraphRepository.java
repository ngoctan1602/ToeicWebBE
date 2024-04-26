package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.TypeParagraph;
import com.tantan.ToeicWeb.response.paragraph.ParagraphByTestAndPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {
    List<Paragraph> findByPart(Part part);
    List<Paragraph> findByPartAndTypeParagraph(Part part, TypeParagraph typeParagraph);

    @Query(
            nativeQuery = true,
            value = "select paragraph.id, paragraph.audio, paragraph.content, paragraph.img from paragraph  \n" +
                    "join part on part.id = paragraph.part_id and part.id =?\n" +
                    "join test_paragraph on test_paragraph.paragraph_id = paragraph.id\n" +
                    "join test on test.id = test_paragraph.test_id and test.id =?"
    )
    List<ParagraphByTestAndPart> getParagraphByTestAndPart(Long partId, Long testId);
}
