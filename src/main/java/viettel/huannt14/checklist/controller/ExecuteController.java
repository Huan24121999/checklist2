package viettel.huannt14.checklist.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.repository.ChecklistHistoryRepo;
import viettel.huannt14.checklist.service.ChecklistHistoryService;
import viettel.huannt14.checklist.service.ExecuteService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.jar.JarEntry;

@RestController
@RequestMapping("/api/v1/private/execute")
public class ExecuteController {

    @Autowired
    private ChecklistHistoryService checklistHistoryService;

    @Autowired
    private ChecklistHistoryRepo checklistHistoryRepo;

    @Autowired
    private ExecuteService executeService;

    @PostMapping("/")
    public ResponseEntity<JsonResult> execute(@RequestBody List<Integer> ids){
        //return JsonResult.success(checklistHistoryRepo.findById(12));
        ChecklistHistory checklistHistory= new ChecklistHistory();
        checklistHistory.setStartTime(new Timestamp(System.currentTimeMillis()));
        List<ResultItem> resultItemList= executeService.execute(ids);
        int countPassed=0;
        for (ResultItem result:resultItemList
             ) {
            if(result.getIsPassed())
                countPassed++;
        }
        checklistHistory.setResult(countPassed+"/"+ids.size());
        String jsonString = new Gson().toJson(resultItemList, ArrayList.class);
        checklistHistory.setDetail(jsonString);
        checklistHistory.setEndTime(new Timestamp(System.currentTimeMillis()));
        return Optional.ofNullable(checklistHistoryService.save(checklistHistory))
                .map(checklistHistoryResult ->{
                    System.out.println(checklistHistoryResult);
                    return JsonResult.success(checklistHistoryResult);
                })
                .orElse(JsonResult.saveError("CheckList History"));

    }
}
