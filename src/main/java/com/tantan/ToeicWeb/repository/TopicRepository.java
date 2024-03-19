package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
