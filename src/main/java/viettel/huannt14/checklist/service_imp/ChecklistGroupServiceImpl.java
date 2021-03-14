package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ChecklistGroup;
import viettel.huannt14.checklist.repository.ChecklistGroupRepo;
import viettel.huannt14.checklist.service.ChecklistGroupService;

import java.util.List;

@Service
public class ChecklistGroupServiceImpl implements ChecklistGroupService {

    private static final Logger logger= LoggerFactory.getLogger(ChecklistGroupServiceImpl.class);

    @Autowired
    private ChecklistGroupRepo checklistGroupRepo;

    @Override
    public ChecklistGroup save(ChecklistGroup checklistGroup) {
        try{
            return checklistGroupRepo.save(checklistGroup);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

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

    @Override
    public List<ChecklistGroup> getAll() {
        try{
            return checklistGroupRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
