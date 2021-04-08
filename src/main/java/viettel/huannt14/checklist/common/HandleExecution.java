package viettel.huannt14.checklist.common;

import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;

import java.util.List;

/**
 *  * interface for handle execution
 *
 * @author huannt14
 */
public interface HandleExecution {

    /**
     * execute list checklist item and return result
     *
     * @param serverInfo server to execute
     * @param checklistItems list checklist item to execute
     * @return list of result of each checklist item
     */
    List<ResultItem> handle(ServerInfo serverInfo, List<ChecklistItem> checklistItems);
}
