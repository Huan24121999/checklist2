package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viettel.huannt14.checklist.entity.ChecklistHistory;

import java.util.List;

@Repository
public interface ChecklistHistoryRepo extends JpaRepository<ChecklistHistory,Integer> {

    @Query(value = "SELECT a FROM ChecklistHistory as a order by a.startTime desc ")
    List<ChecklistHistory> findAll();
}
