package com.tantan.ToeicWeb.response.question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.io.Serializable;

@Entity
@Table(name = "question_by_part")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionByPart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "audio")
    private String audio;
    @Column(name = "part_id")
    private Long partId;
}
