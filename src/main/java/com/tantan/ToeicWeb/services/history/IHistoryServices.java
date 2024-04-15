package com.tantan.ToeicWeb.services.history;

import com.tantan.ToeicWeb.request.history.HistoryRequest;
import com.tantan.ToeicWeb.response.history.HistoryResponse;

import java.util.List;

public interface IHistoryServices {
    public boolean createNewHistory (HistoryRequest historyRequest);
    public List<HistoryResponse> getHistoryWithTestAndUser(Long testId);
}
