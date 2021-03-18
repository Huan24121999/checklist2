package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.service.ChecklistItemService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/checklist-item/")
public class ChecklistItemController {

    @Autowired
    private ChecklistItemService checklistItemService;

    @PostMapping("/upload")
    private ResponseEntity<JsonResult> upload(@RequestBody ChecklistItem checklistItem){
        return Optional.ofNullable(checklistItemService.save(checklistItem))
                .map(checklistItemResult -> {
                    return JsonResult.uploaded(checklistItemResult);
                })
                .orElse(JsonResult.saveError("ChecklistItem"));
    }

    @PutMapping("/update")
    private ResponseEntity<JsonResult> update(@RequestBody ChecklistItem checklistItem){
        return Optional.ofNullable(checklistItemService.save(checklistItem))
                .map(checklistItemResult -> {
                    return JsonResult.updated(checklistItemResult);
                })
                .orElse(JsonResult.saveError("ChecklistItem"));
    }

    @DeleteMapping("/delete")
    private ResponseEntity<JsonResult> deleteById(@RequestParam(name = "id")Integer id){
        Boolean deleted=checklistItemService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ChecklistItem");
    }

    @GetMapping("/get-all")
    private ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(checklistItemService.getAll())
                .map(checklistItemResults -> {
                    return JsonResult.found(checklistItemResults);
                })
                .orElse(JsonResult.notFound("ChecklistItem"));
    }

    @GetMapping("/find-by-id")
    private ResponseEntity<JsonResult> findById(@RequestParam(name = "id")Integer id){
        return checklistItemService.findById(id)
                .map(checklistItem -> {
                    return JsonResult.found(checklistItem);
                })
                .orElse(JsonResult.notFound("Checklist Item"));
    }
}
