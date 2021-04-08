package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistGroup;
import viettel.huannt14.checklist.service.ChecklistGroupService;

import java.util.Optional;

/**
 * controller to handle http request regarding to Checklist Group
 *
 * @author huannt14
 */
@RestController
@RequestMapping("/api/v1/private/checklist-group")
public class ChecklistGroupController {

    @Autowired
    private ChecklistGroupService checklistGroupService;

    /**
     * upload new checklistGroup
     *
     * @param checklistGroup checklist group needed to upload
     * @return saved checklist group
     */
    @PostMapping("/upload")
    private ResponseEntity<JsonResult> upload(@RequestBody ChecklistGroup checklistGroup){
        return Optional.ofNullable(checklistGroupService.save(checklistGroup))
                .map(checklistGroupResult ->{
                    return JsonResult.uploaded(checklistGroupResult);
                })
                .orElse(JsonResult.saveError("ChecklistGroup"));
    }

    /**
     * update a checklist group
     *
     * @param checklistGroup checklist group needed to update
     * @return updated checklist group
     */
    @PutMapping("/update")
    private ResponseEntity<JsonResult> update(@RequestBody ChecklistGroup checklistGroup){
        return Optional.ofNullable(checklistGroupService.save(checklistGroup))
                .map(checklistGroupResult ->{
                    return JsonResult.updated(checklistGroupResult);
                })
                .orElse(JsonResult.saveError("ChecklistGroup"));
    }

    /**
     * delete a checklist group by id
     * @param id id's checklist group
     * @return deleted= true/false
     */
    @DeleteMapping("/delete")
    private ResponseEntity<JsonResult> deleteById(@RequestParam(name = "id") Integer id){
        Boolean deleted=checklistGroupService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ChecklistGroup");
    }

    /**
     * find all checklist groups
     * @return a list has all checklist groups
     */
    @GetMapping("/find-all")
    private ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(checklistGroupService.findAll())
                .map(checklistGroups -> {
                    return JsonResult.found(checklistGroups);
                })
                .orElse(JsonResult.notFound("ChecklistGroup"));
    }
}
