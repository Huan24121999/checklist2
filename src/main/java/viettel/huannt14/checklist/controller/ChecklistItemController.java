package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.service.ChecklistItemService;

import java.util.Optional;

/**
 * controller to handle http request regarding to Checklist Item
 *
 * @author huannt14
 */
@RestController
@RequestMapping("/api/v1/private/checklist-item/")
public class ChecklistItemController {

    @Autowired
    private ChecklistItemService checklistItemService;

    /**
     * upload new Checklist Item
     *
     * @param checklistItem checklist item needed to upload
     * @return upload checklist Item
     */
    @PostMapping("/upload")
    private ResponseEntity<JsonResult> upload(@RequestBody ChecklistItem checklistItem){
        return Optional.ofNullable(checklistItemService.save(checklistItem))
                .map(checklistItemResult -> {
                    return JsonResult.uploaded(checklistItemResult);
                })
                .orElse(JsonResult.saveError("ChecklistItem"));
    }

    /**
     * update a Checklist Item
     *
     * @param checklistItem checklist item needed to update
     * @return update checklist Item
     */
    @PutMapping("/update")
    private ResponseEntity<JsonResult> update(@RequestBody ChecklistItem checklistItem){
        return Optional.ofNullable(checklistItemService.save(checklistItem))
                .map(checklistItemResult -> {
                    return JsonResult.updated(checklistItemResult);
                })
                .orElse(JsonResult.saveError("ChecklistItem"));
    }

    /**
     * delete checklist item by id's checklist item
     *
     * @param id id's checklist item
     * @return deleted= true/false
     */
    @DeleteMapping("/delete")
    private ResponseEntity<JsonResult> deleteById(@RequestParam(name = "id")Integer id){
        Boolean deleted=checklistItemService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ChecklistItem");
    }

    /**
     * find all checklist item
     *
     * @return a list has all checklist items
     */
    @GetMapping("/get-all")
    private ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(checklistItemService.findAll())
                .map(checklistItemResults -> {
                    return JsonResult.found(checklistItemResults);
                })
                .orElse(JsonResult.notFound("ChecklistItem"));
    }

    /**
     * find checklist item by id's checklist item
     *
     * @param id id's checklist item
     * @return satisfied checklist item
     */
    @GetMapping("/find-by-id")
    private ResponseEntity<JsonResult> findById(@RequestParam(name = "id")Integer id){
        return checklistItemService.findById(id)
                .map(checklistItem -> {
                    return JsonResult.found(checklistItem);
                })
                .orElse(JsonResult.notFound("Checklist Item"));
    }

    /**
     * find checklist item by id's server
     *
     * @param serverId id's server
     * @return list checklist items
     */
    @GetMapping("/find-by-server-id")
    private ResponseEntity<JsonResult> findByServerId(@RequestParam(name = "server-id")Integer serverId){
        return Optional.ofNullable(checklistItemService.findByServerId(serverId))
                .map(checklistItems -> {
                    return JsonResult.found(checklistItems);
                })
                .orElse(JsonResult.notFound("Checklist Item"));
    }
}
