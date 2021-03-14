package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.service.ChecklistHistoryService;
import viettel.huannt14.checklist.service.ExecuteService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.jar.JarEntry;

@RestController
@RequestMapping("/api/v1/private/execute")
public class ExecuteController {

    @Autowired
    private ChecklistHistoryService checklistHistoryService;

    @Autowired
    private ExecuteService executeService;

    @GetMapping("/")
    public ResponseEntity<JsonResult> execute(@RequestBody List<Integer> ids){
        return JsonResult.found(executeService.execute(ids));
    }
}
