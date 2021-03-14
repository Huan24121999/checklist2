package viettel.huannt14.checklist.common;

import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;

import java.util.List;

public interface ExecuteHandle {

    List<ResultItem> handle(ServerInfo serverInfo, List<ChecklistItem> checklistItems);
}
