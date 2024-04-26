package com.tantan.ToeicWeb.response.history;

import com.tantan.ToeicWeb.response.PartResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface HistoryOverView {
     Long getId();
     Long getTotalCorrect();
     Date getDateCompleted();
     Time getTotalTime();
     int getTotalQuestion();
     String getName();
}
