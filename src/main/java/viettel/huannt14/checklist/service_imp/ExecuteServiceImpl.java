package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.common.ExecuteHandle;
import viettel.huannt14.checklist.common.RestApiExecute;
import viettel.huannt14.checklist.common.SshExecute;
import viettel.huannt14.checklist.entity.CheckType;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;
import viettel.huannt14.checklist.repository.ChecklistItemRepo;
import viettel.huannt14.checklist.service.ExecuteService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ExecuteServiceImpl implements ExecuteService {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteServiceImpl.class);

    @Autowired
    private ChecklistItemRepo checklistItemRepo;

    private ExecuteHandle executeHandle = null;

    @Override
    public List<ResultItem> execute(List<Integer> itemIds) {
        List<ChecklistItem> checklistItems = checklistItemRepo.findById(itemIds);
        List<List<ChecklistItem>> checkList = new ArrayList<>();
        int itemSize = checklistItems.size();

        for (int i = 0; i < itemSize; i++) {
            List<ChecklistItem> checklistItemsTemp = new ArrayList<>();
            ChecklistItem previous = checklistItems.get(i);
            checklistItemsTemp.add(previous);
            int j = 0;
            for (j = i + 1; j < itemSize; j++) {
                ChecklistItem recentItem = checklistItems.get(j);
                if (previous.getTypeCheck() != recentItem.getTypeCheck() || previous.getServer() == null || recentItem.getServer() == null || previous.getServer().getId() != recentItem.getServer().getId()) {
                    i = j - 1;
                    break;
                } else {
                    checklistItemsTemp.add(recentItem);
                }
            }
            checkList.add(checklistItemsTemp);
            if (j == itemSize)
                break;
        }
        System.out.println("none");
        return executeThread(checkList);
    }

    private List<ResultItem> executeThread(List<List<ChecklistItem>> checklistItemsList) {
        List<ResultItem> resultItemList = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (List<ChecklistItem> checklistItems : checklistItemsList
        ) {
            Thread thread = new Thread(() -> {
                ChecklistItem standardItem = checklistItems.get(0);
                ExecuteHandle executeHandle = null;
                ServerInfo serverInfo = standardItem.getServer();
                if (standardItem.getTypeCheck() == CheckType.SERVER_CHECK) {
                    executeHandle = new SshExecute();
                } else if (standardItem.getTypeCheck() == CheckType.API_CHECK) {
                    executeHandle = new RestApiExecute();
                }
                List<ResultItem> resultItemListExecuted = executeHandle.handle(serverInfo, checklistItems);
                synchronized (resultItemList) {
                    if (resultItemListExecuted != null)
                        resultItemList.addAll(resultItemListExecuted);
                }
            });
            executor.execute(thread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        return resultItemList;
    }

}
