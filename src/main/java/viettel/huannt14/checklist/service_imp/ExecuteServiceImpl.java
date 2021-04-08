package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.common.HandleExecution;
import viettel.huannt14.checklist.common.RestApiExecution;
import viettel.huannt14.checklist.common.SshExecution;
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

/**
 * Implementation of ExecuteService
 *
 * @author huannt14
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteServiceImpl.class);

    @Autowired
    private ChecklistItemRepo checklistItemRepo;

    private HandleExecution handleExecution = null;

    /**
     * execute list test cases ( list items) by id's items
     *
     * @param itemIds list id's items need to execute
     * @return a list has executed results
     */
    @Override
    public List<ResultItem> execute(List<Integer> itemIds) {
        // find checklist items on db by list input id's items
        List<ChecklistItem> checklistItems = checklistItemRepo.findById(itemIds);
        // init a list contain list of items
        List<List<ChecklistItem>> listItemList = new ArrayList<>();
        int itemSize = checklistItems.size();

        // divide checklist items to lists(groups) base on type of check and server check
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
            listItemList.add(checklistItemsTemp);
            if (j == itemSize)
                break;
        }
        return executeListItemList(listItemList);
    }

    /**
     * execute a list of list of items, each list of items create a thread to execute
     *
     * @param checklistItemsList list of list items
     * @return list of result item, each item present of result of execution of each
     * checklist item
     */
    private List<ResultItem> executeListItemList(List<List<ChecklistItem>> checklistItemsList) {
        List<ResultItem> resultItemList = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();

        for (List<ChecklistItem> checklistItems : checklistItemsList
        ) {
            Thread thread = new Thread(() -> {
                ChecklistItem standardItem = checklistItems.get(0);
                HandleExecution handleExecution = null;
                ServerInfo serverInfo = standardItem.getServer();
                // init executor base on type of check
                if (standardItem.getTypeCheck() == CheckType.SERVER_CHECK) {
                    handleExecution = new SshExecution();
                } else if (standardItem.getTypeCheck() == CheckType.API_BOOLEAN_CHECK || standardItem.getTypeCheck() == CheckType.API_DATA_CHECK) {
                    handleExecution = new RestApiExecution();
                }

                List<ResultItem> resultItemListExecuted = handleExecution.handle(serverInfo, checklistItems);
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
