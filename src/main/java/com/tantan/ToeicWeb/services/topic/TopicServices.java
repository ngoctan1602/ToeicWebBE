package com.tantan.ToeicWeb.services.topic;

import com.tantan.ToeicWeb.entity.Topic;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.repository.TopicRepository;
import com.tantan.ToeicWeb.request.TopicRequest;
import com.tantan.ToeicWeb.response.DataResponse;
import com.tantan.ToeicWeb.response.TopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServices implements ITopicServices{
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public boolean addTopic(TopicRequest topicRequest) {
        Topic topic = new Topic();
        topic.setName(topicRequest.getName());
        topic.setDescription(topicRequest.getDescription());
        try{
            topicRepository.save(topic);
            return true;
        }
        catch (Exception e)
        {
            throw new CustomException(new DataResponse(true, HttpStatus.INTERNAL_SERVER_ERROR.value(),"Tên bộ đề đã tồn tại",null ));
        }

    }

    @Override
    public List<TopicResponse> getAllTopic() {
        List<TopicResponse> topicResponses= topicRepository.findAll().stream().map(
                topic -> new TopicResponse(
                        topic.getId(),
                        topic.getName(),
                        topic.getDescription()
                )
        ).collect(Collectors.toList());
        if(!topicResponses.isEmpty()){
            return topicResponses;
        }
        throw new CustomException(new DataResponse(false,HttpStatus.NOT_FOUND.value(), "Không tìm thấy bộ đề",null));
    }
}
