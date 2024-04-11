package com.tantan.ToeicWeb.services.paragraph;

import com.tantan.ToeicWeb.entity.Paragraph;
import com.tantan.ToeicWeb.entity.Part;
import com.tantan.ToeicWeb.entity.TypeParagraph;
import com.tantan.ToeicWeb.mapper.ParagraphMapper;
import com.tantan.ToeicWeb.mapper.QuestionMapper;
import com.tantan.ToeicWeb.repository.ParagraphRepository;
import com.tantan.ToeicWeb.repository.PartRepository;
import com.tantan.ToeicWeb.repository.QuestionRepository;
import com.tantan.ToeicWeb.repository.TypeParagraphRepository;
import com.tantan.ToeicWeb.response.ParagraphResponse;
import com.tantan.ToeicWeb.response.question.QuestionNoneAnswerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParagraphServices implements IParagraphServices{
    @Autowired
    private ParagraphRepository paragraphRepository;
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private TypeParagraphRepository typeParagraphRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public List<ParagraphResponse> getAllQuestionByPartAndType(Long idPart, Long idType) {
        Part part = partRepository.findById(idPart).orElse(null);
        TypeParagraph typeParagraph = typeParagraphRepository.findById(idType).orElse(null);
        if(part!=null && typeParagraph !=null)
        {
//            return paragraphRepository.findByPartAndTypeParagraph(part,typeParagraph).stream().map(
//                   ParagraphMapper.INSTANCE::toDTO
//           ).toList();
            List<Paragraph> paragraphs=paragraphRepository.findByPartAndTypeParagraph(part,typeParagraph);
            List<ParagraphResponse> paragraphResponses = new ArrayList<>();
            for (Paragraph paragraph: paragraphs)
            {
               ParagraphResponse paragraphResponse= ParagraphMapper.INSTANCE.toDTO(paragraph);
                List<QuestionNoneAnswerResponse> questions = questionRepository.findByParagraph(paragraph).stream().map(
                        QuestionMapper.INSTANCE::toQuestionResponse
                ).toList();
                paragraphResponse.setListQues(questions);
                paragraphResponses.add(paragraphResponse);
            }
            return paragraphResponses;
        }

        return null;
    }
}
