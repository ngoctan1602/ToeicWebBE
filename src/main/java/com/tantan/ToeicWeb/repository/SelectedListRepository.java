package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.history.SelectedList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedListRepository extends JpaRepository<SelectedList,Long> {
}
