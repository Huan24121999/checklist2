package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.entity.ChecklistItem;

import java.util.List;
import java.util.Optional;

/**
 * service is provided to interact with Checklist Item
 *
 * @author huannt14
 */
public interface ChecklistItemService {
    ChecklistItem save(ChecklistItem checklistItem);

    Boolean deleteById(Integer id);

    List<ChecklistItem> findAll();

    Optional<ChecklistItem> findById(Integer id);

    List<ChecklistItem> findByServerId(Integer serverId);
}
