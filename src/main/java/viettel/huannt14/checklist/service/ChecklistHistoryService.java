package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ChecklistHistory;

import java.util.List;

/**
 * service is provided to interact with Checklist History
 *
 * @author huannt14
 */
public interface ChecklistHistoryService {
    ChecklistHistory save(ChecklistHistory checklistHistory);

    Boolean deleteById(Integer id);

    List<ChecklistHistory> findAll();
}
