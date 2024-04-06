package com.tantan.ToeicWeb.services.paragraph;

import com.tantan.ToeicWeb.mapper.TypeParagraphMapper;
import com.tantan.ToeicWeb.repository.TypeParagraphRepository;
import com.tantan.ToeicWeb.response.TypeParagraphResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeParagraphServices implements ITypeParagraphServices {
    @Autowired
    private TypeParagraphRepository typeParagraphRepository;
    @Override
    public List<TypeParagraphResponse> getAllTypeParagraph() {
        List<TypeParagraphResponse> typeParagraphResponses = typeParagraphRepository.findAll().stream().map(
                type ->TypeParagraphMapper.INSTANCE.toDTO(type)
        ).collect(Collectors.toList());
        return typeParagraphResponses;
    }
}
