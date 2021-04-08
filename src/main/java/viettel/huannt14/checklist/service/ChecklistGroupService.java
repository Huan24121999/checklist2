package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ChecklistGroup;

import java.util.List;

/**
 * service is provided to interact with Checklist Group
 *
 * @author huannt14
 */
public interface ChecklistGroupService {
    ChecklistGroup save(ChecklistGroup checklistGroup);

    Boolean deleteById(Integer id);

    List<ChecklistGroup> findAll();
}
