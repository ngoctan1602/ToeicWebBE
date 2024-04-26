package com.tantan.ToeicWeb.services.history;

import com.tantan.ToeicWeb.request.history.HistoryRequest;
import com.tantan.ToeicWeb.response.history.HistoryResponse;
import com.tantan.ToeicWeb.response.history.QuestionWithSelectedAndAnswer;
import com.tantan.ToeicWeb.response.history.QuestionWithSelectedResponse;
import com.tantan.ToeicWeb.response.history.SelectedListResponse;

import java.util.List;

public interface IHistoryServices {
    public boolean createNewHistory (HistoryRequest historyRequest);
    public List<HistoryResponse> getHistoryWithTestAndUser(Long testId);

    public List<QuestionWithSelectedAndAnswer> getSelectedListByHistoryAndPart(Long historyId, Long idTest,Long partId);
    public HistoryResponse getHistoryOverView(Long testId, Long historyId);
}
