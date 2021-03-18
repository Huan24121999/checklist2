package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import viettel.huannt14.checklist.entity.ChecklistItem;

import java.util.List;

@Repository
public interface ChecklistItemRepo extends JpaRepository<ChecklistItem,Integer> {

    @Query(value ="select a from ChecklistItem as a where a.id in ?1 order by a.typeCheck desc , a.server.id")
    List<ChecklistItem> findById(List<Integer> itemIds);

    @Query(value = "select a from ChecklistItem as a ORDER BY a.checklistGroup.id")
    List<ChecklistItem> findAll();
}
