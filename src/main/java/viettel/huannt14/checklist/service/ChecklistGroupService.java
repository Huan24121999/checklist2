package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ChecklistGroup;

import java.util.List;

public interface ChecklistGroupService {
    ChecklistGroup save(ChecklistGroup checklistGroup);

    Boolean deleteById(Long id);

    List<ChecklistGroup> getAll();
}
