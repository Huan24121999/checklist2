package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.repository.ChecklistHistoryRepo;
import viettel.huannt14.checklist.service.ChecklistHistoryService;

import java.util.List;

/**
 * Implementation of ChecklistHistoryService
 *
 * @author huannt14
 */
@Service
public class ChecklistHistoryServiceImpl implements ChecklistHistoryService {
    private static final Logger logger= LoggerFactory.getLogger(ChecklistGroupServiceImpl.class);

    @Autowired
    private ChecklistHistoryRepo checklistHistoryRepo;

    /**
     * save a Checklist History
     *
     * @param checklistHistory Checklist history needed to save on database
     * @return stored Checklist History
     */
    @Override
    public ChecklistHistory save(ChecklistHistory checklistHistory) {
        try{
            return checklistHistoryRepo.save(checklistHistory);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * delete a checklist history by id's history
     *
     * @param id id's history
     * @return true if deleted, otherwise return false
     */
    @Override
    public Boolean deleteById(Integer id) {
        try{
            checklistHistoryRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return false;
        }
    }

    /**
     * find all checklist exist history
     *
     * @return a list has all checklist histories on db
     */
    @Override
    public List<ChecklistHistory> findAll() {
        try{
            return checklistHistoryRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
