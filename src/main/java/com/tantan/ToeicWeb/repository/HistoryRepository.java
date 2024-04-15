package com.tantan.ToeicWeb.repository;

import com.tantan.ToeicWeb.entity.Test;
import com.tantan.ToeicWeb.entity.User;
import com.tantan.ToeicWeb.entity.history.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query(nativeQuery = true, value = "call totalCorrectChoose(?,?,?,?)")
    Integer getTotalCorrect(
            Long historyId,
            Long testId,
            Long userId,
            Long partID
    );


    List<History> findByTestAndUser(Test test, User user);

}
