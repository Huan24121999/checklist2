package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.service.ChecklistHistoryService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/checklist-history/")
public class ChecklistHistoryController {

    @Autowired
    private ChecklistHistoryService checklistHistoryService;

    @PostMapping("/upload")
    private ResponseEntity<JsonResult> upload(@RequestBody ChecklistHistory checklistHistory){
        return Optional.ofNullable(checklistHistoryService.save(checklistHistory))
                .map(checklistHistoryResult ->{
                    return JsonResult.uploaded(checklistHistoryResult);
                })
                .orElse(JsonResult.saveError("ChecklistHistory"));
    }

    @PutMapping("/update")
    private ResponseEntity<JsonResult> update(@RequestBody ChecklistHistory checklistHistory){
        return Optional.ofNullable(checklistHistoryService.save(checklistHistory))
                .map(checklistHistoryResult ->{
                    return JsonResult.updated(checklistHistoryResult);
                })
                .orElse(JsonResult.saveError("ChecklistHistory"));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<JsonResult> deleteById(@RequestParam(name = "id") Integer id){
        Boolean deleted= checklistHistoryService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ChecklistHistory");
    }

    @GetMapping("/find-all")
    private ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(checklistHistoryService.getAll())
                .map(checklistHistories -> {
                    return JsonResult.found(checklistHistories);
                })
                .orElse(JsonResult.notFound("ChecklistHistory"));
    }


}
