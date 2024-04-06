package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.TypeParagraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {
    List<Paragraph> findByPart(Part part);
    List<Paragraph> findByPartAndTypeParagraph(Part part, TypeParagraph typeParagraph);
}
