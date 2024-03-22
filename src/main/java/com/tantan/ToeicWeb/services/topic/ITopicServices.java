package com.tantan.ToeicWeb.services.topic;

import com.tantan.ToeicWeb.request.TopicRequest;
import com.tantan.ToeicWeb.response.TopicResponse;

import java.util.List;

public interface ITopicServices {
    public boolean addTopic(TopicRequest topicRequest);
    public List<TopicResponse> getAllTopic();
}
