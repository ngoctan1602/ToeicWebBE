package com.tantan.ToeicWeb.services.history;

import com.tantan.ToeicWeb.request.history.HistoryRequest;

public interface IHistoryServices {
    public boolean createNewHistory (HistoryRequest historyRequest);
}
