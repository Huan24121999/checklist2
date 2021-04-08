package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.service.ChecklistHistoryService;

import java.util.Optional;

/**
 * controller to handle http request regarding to Checklist History
 *
 * @author huannt14
 */
@RestController
@RequestMapping("/api/v1/private/checklist-history/")
public class ChecklistHistoryController {

    @Autowired
    private ChecklistHistoryService checklistHistoryService;

//    @PostMapping("/upload")
//    private ResponseEntity<JsonResult> upload(@RequestBody ChecklistHistory checklistHistory){
//        return Optional.ofNullable(checklistHistoryService.save(checklistHistory))
//                .map(checklistHistoryResult ->{
//                    return JsonResult.uploaded(checklistHistoryResult);
//                })
//                .orElse(JsonResult.saveError("ChecklistHistory"));
//    }

//    @PutMapping("/update")
//    private ResponseEntity<JsonResult> update(@RequestBody ChecklistHistory checklistHistory){
//        return Optional.ofNullable(checklistHistoryService.save(checklistHistory))
//                .map(checklistHistoryResult ->{
//                    return JsonResult.updated(checklistHistoryResult);
//                })
//                .orElse(JsonResult.saveError("ChecklistHistory"));
//    }

    /**
     * delete checklist history by id
     * @param id id's checklist history
     * @return deleted= true/ false
     */
    @DeleteMapping("/delete")
    private ResponseEntity<JsonResult> deleteById(@RequestParam(name = "id") Integer id){
        Boolean deleted= checklistHistoryService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ChecklistHistory");
    }

    /**
     * find all checklist histories
     * @return list of checklist histories
     */
    @GetMapping("/find-all")
    private ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(checklistHistoryService.findAll())
                .map(checklistHistories -> {
                    return JsonResult.found(checklistHistories);
                })
                .orElse(JsonResult.notFound("ChecklistHistory"));
    }


}
