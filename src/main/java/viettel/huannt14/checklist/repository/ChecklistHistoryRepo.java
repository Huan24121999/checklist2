package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.huannt14.checklist.entity.ChecklistHistory;

public interface ChecklistHistoryRepo extends JpaRepository<ChecklistHistory,Long> {
}
