package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.repository.ChecklistItemRepo;
import viettel.huannt14.checklist.service.ChecklistItemService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ChecklistItemService
 *
 * @author huannt14
 */
@Service
public class ChecklistItemServiceImpl implements ChecklistItemService {

    private static final Logger logger= LoggerFactory.getLogger(ChecklistItemServiceImpl.class);

    @Autowired
    private ChecklistItemRepo checklistItemRepo;

    /**
     * save a checklist item on database
     *
     * @param checklistItem checklist item needed to save
     * @return stored item
     */
    @Override
    public ChecklistItem save(ChecklistItem checklistItem) {
        try{
            return checklistItemRepo.save(checklistItem);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * delete a checklist item by id's checklist item
     * @param id id's checklist item
     * @return true if deleted, otherwise return false
     */
    @Override
    public Boolean deleteById(Integer id) {
        try{
            checklistItemRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return false;
        }
    }

    /**
     * find all checklist items on db
     *
     * @return a list has all checklist items
     */
    @Override
    public List<ChecklistItem> findAll() {
        try{
            return checklistItemRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * find a checklist item by id's checklist item
     * @param id id's checklist item
     * @return a Optional of checklist item
     */
    @Override
    public Optional<ChecklistItem> findById(Integer id) {
        try{
            return checklistItemRepo.findById(id);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return Optional.empty();
        }
    }

    /**
     * find checklist items by id's server
     *
     * @param serverId id's server
     * @return a list has satisfied checklist items
     */
    @Override
    public List<ChecklistItem> findByServerId(Integer serverId) {
        try{
            return checklistItemRepo.findAllByServerId(serverId);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
