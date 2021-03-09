package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.huannt14.checklist.entity.ChecklistGroup;

public interface ChecklistGroupRepo extends JpaRepository<ChecklistGroup,Long> {
}
