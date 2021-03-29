package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.entity.ChecklistItem;

import java.util.List;
import java.util.Optional;

public interface ChecklistItemService {
    ChecklistItem save(ChecklistItem checklistItem);

    Boolean deleteById(Integer id);

    List<ChecklistItem> getAll();

    Optional<ChecklistItem> findById(Integer id);

    List<ChecklistItem> findByServerId(Integer serverId);
}
