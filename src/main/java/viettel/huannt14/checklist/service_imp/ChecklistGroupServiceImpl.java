package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ChecklistGroup;
import viettel.huannt14.checklist.repository.ChecklistGroupRepo;
import viettel.huannt14.checklist.service.ChecklistGroupService;

import java.util.List;

/**
 * Implementation of ChecklistGroupService
 *
 * @author huannt14
 */
@Service
public class ChecklistGroupServiceImpl implements ChecklistGroupService {

    private static final Logger logger= LoggerFactory.getLogger(ChecklistGroupServiceImpl.class);

    @Autowired
    private ChecklistGroupRepo checklistGroupRepo;

    /**
     * save a Group to database
     *
     * @param checklistGroup Group needed to store
     * @return stored checklist Group
     */
    @Override
    public ChecklistGroup save(ChecklistGroup checklistGroup) {
        try{
            return checklistGroupRepo.save(checklistGroup);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * delete a Group by Id's Group
     *
     * @param id id's group
     * @return true if deleted, otherwise: false
     */
    @Override
    public Boolean deleteById(Integer id) {
        try{
            checklistGroupRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return false;
        }
    }

    /**
     * find all Groups
     *
     * @return a list has all groups
     */
    @Override
    public List<ChecklistGroup> findAll() {
        try{
            return checklistGroupRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
