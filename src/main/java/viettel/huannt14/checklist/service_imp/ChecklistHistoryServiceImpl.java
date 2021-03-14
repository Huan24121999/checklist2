package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.repository.ChecklistHistoryRepo;
import viettel.huannt14.checklist.service.ChecklistHistoryService;

import java.util.List;

@Service
public class ChecklistHistoryServiceImpl implements ChecklistHistoryService {
    private static final Logger logger= LoggerFactory.getLogger(ChecklistGroupServiceImpl.class);

    @Autowired
    private ChecklistHistoryRepo checklistHistoryRepo;

    @Override
    public ChecklistHistory save(ChecklistHistory checklistHistory) {
        try{
            return checklistHistoryRepo.save(checklistHistory);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

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

    @Override
    public List<ChecklistHistory> getAll() {
        try{
            return checklistHistoryRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
