package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistGroup;
import viettel.huannt14.checklist.service.ChecklistGroupService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/checklist-group")
public class ChecklistGroupController {

    @Autowired
    private ChecklistGroupService checklistGroupService;

    @PostMapping("/upload")
    private ResponseEntity<JsonResult> upload(@RequestBody ChecklistGroup checklistGroup){
        return Optional.ofNullable(checklistGroupService.save(checklistGroup))
                .map(checklistGroupResult ->{
                    return JsonResult.uploaded(checklistGroupResult);
                })
                .orElse(JsonResult.saveError("ChecklistGroup"));
    }

    @PutMapping("/update")
    private ResponseEntity<JsonResult> update(@RequestBody ChecklistGroup checklistGroup){
        return Optional.ofNullable(checklistGroupService.save(checklistGroup))
                .map(checklistGroupResult ->{
                    return JsonResult.updated(checklistGroupResult);
                })
                .orElse(JsonResult.saveError("ChecklistGroup"));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<JsonResult> deleteById(@RequestParam(name = "id") Integer id){
        Boolean deleted=checklistGroupService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ChecklistGroup");
    }

    @GetMapping("/get-all")
    private ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(checklistGroupService.getAll())
                .map(checklistGroups -> {
                    return JsonResult.found(checklistGroups);
                })
                .orElse(JsonResult.notFound("ChecklistGroup"));
    }
}
