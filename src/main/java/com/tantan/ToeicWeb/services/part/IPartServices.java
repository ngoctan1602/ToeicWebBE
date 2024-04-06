package com.tantan.ToeicWeb.services.part;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.response.PartResponse;

import java.util.List;

public interface IPartServices {
    public List<PartResponse> getAllPart();
    public List<Paragraph> getParagraphByPart(Long idPart);
}
