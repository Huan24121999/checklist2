package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ChecklistGroup;
import viettel.huannt14.checklist.entity.ChecklistHistory;

import java.util.List;

public interface ChecklistHistoryService {
    ChecklistHistory save(ChecklistHistory checklistHistory);

    Boolean deleteById(Integer id);

    List<ChecklistHistory> getAll();
}
