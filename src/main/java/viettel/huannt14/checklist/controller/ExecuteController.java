package viettel.huannt14.checklist.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ChecklistHistory;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.repository.ChecklistHistoryRepo;
import viettel.huannt14.checklist.service.ChecklistHistoryService;
import viettel.huannt14.checklist.service.ExecuteService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * controller handle http request to execute checklist items
 */
@RestController
@RequestMapping("/api/v1/private/execute")
public class ExecuteController {

    @Autowired
    private ChecklistHistoryService checklistHistoryService;

    @Autowired
    private ChecklistHistoryRepo checklistHistoryRepo;

    @Autowired
    private ExecuteService executeService;

    /**
     * execute checklist items by list id of checklist items
     *
     * @param ids list id of checklist items needed to execute
     * @return a Executive History to represent for result of execution
     */
    @PostMapping("/")
    public ResponseEntity<JsonResult> execute(@RequestBody List<Integer> ids){
        // init checklist history to store result of execution
        ChecklistHistory checklistHistory= new ChecklistHistory();
        checklistHistory.setStartTime(new Timestamp(System.currentTimeMillis()));
        // call execution service to execute
        List<ResultItem> resultItemList= executeService.execute(ids);

        //count passed test case( checklist item)
        int countPassed=0;
        for (ResultItem result:resultItemList
             ) {
            if(result.getIsPassed())
                countPassed++;
        }
        checklistHistory.setResult(countPassed+"/"+ids.size());
        // convert list result to Json, it's help easily read this field at frontend
        String resultJson = new Gson().toJson(resultItemList, ArrayList.class);
        checklistHistory.setDetail(resultJson);
        checklistHistory.setEndTime(new Timestamp(System.currentTimeMillis()));
        return Optional.ofNullable(checklistHistoryService.save(checklistHistory))
                .map(checklistHistoryResult ->{
                    System.out.println(checklistHistoryResult);
                    return JsonResult.success(checklistHistoryResult);
                })
                .orElse(JsonResult.saveError("CheckList History"));

    }
}
