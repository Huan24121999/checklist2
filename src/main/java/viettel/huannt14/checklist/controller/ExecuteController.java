package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.service.ChecklistHistoryService;

import java.util.List;
import java.util.jar.JarEntry;

@RestController
@RequestMapping("/api/v1/private/execute")
public class ExecuteController {

    @Autowired
    private ChecklistHistoryService checklistHistoryService;

    @GetMapping("/")
    public ResponseEntity<JsonResult> execute(@RequestBody List<Long> ids){
        return null;
    }
}
