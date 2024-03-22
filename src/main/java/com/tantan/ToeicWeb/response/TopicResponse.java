package com.tantan.ToeicWeb.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {
    private Long id;
    private String name;
    private String description;
}
