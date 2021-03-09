package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.huannt14.checklist.entity.ChecklistItem;

public interface ChecklistItemRepo extends JpaRepository<ChecklistItem,Long> {
}
