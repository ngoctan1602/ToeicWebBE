package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.history.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Long> {
}
