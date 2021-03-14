package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.repository.ChecklistItemRepo;
import viettel.huannt14.checklist.service.ChecklistItemService;

import java.util.List;

@Service
public class ChecklistItemServiceImpl implements ChecklistItemService {

    private static final Logger logger= LoggerFactory.getLogger(ChecklistItemServiceImpl.class);

    @Autowired
    private ChecklistItemRepo checklistItemRepo;

    @Override
    public ChecklistItem save(ChecklistItem checklistItem) {
        try{
            return checklistItemRepo.save(checklistItem);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

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

    @Override
    public List<ChecklistItem> getAll() {
        try{
            return checklistItemRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
