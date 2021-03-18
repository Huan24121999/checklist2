package viettel.huannt14.checklist.service_imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.common.ExecuteHandle;
import viettel.huannt14.checklist.common.RestApiExecute;
import viettel.huannt14.checklist.common.SshExecute;
import viettel.huannt14.checklist.entity.*;
import viettel.huannt14.checklist.repository.ChecklistItemRepo;
import viettel.huannt14.checklist.service.ExecuteService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class ExecuteServiceImpl implements ExecuteService {

    @Autowired
    private ChecklistItemRepo checklistItemRepo;

    private ExecuteHandle executeHandle = null;

    @Override
    public List<ResultItem> execute(List<Integer> itemIds) {
        //return demo2(itemIds);
        List<ChecklistItem> checklistItems = checklistItemRepo.findById(itemIds);
        List<List<ChecklistItem>> checkList = new ArrayList<>();
        int itemSize = checklistItems.size();

        for (int i = 0; i < itemSize ; i++) {
            List<ChecklistItem> checklistItemsTemp = new ArrayList<>();
            ChecklistItem previous = checklistItems.get(i);
            checklistItemsTemp.add(previous);
            int j=0;
            for (j = i + 1; j < itemSize; j++) {
                ChecklistItem recentItem = checklistItems.get(j);
                if (previous.getTypeCheck() != recentItem.getTypeCheck() || previous.getServer()==null || recentItem.getServer()==null ||previous.getServer().getId() != recentItem.getServer().getId()) {
                    i = j - 1;
                    break;
                } else {
                    checklistItemsTemp.add(recentItem);
                }
            }
            checkList.add(checklistItemsTemp);
            if(j==itemSize)
                break;
        }
        System.out.println("none");
        return executeThread(checkList);
    }

    private List<ResultItem> demo2(List<Integer> ids){
        List<ChecklistItem> checklistItems= checklistItemRepo.findById(ids);
        List<ResultItem> c=new ArrayList<>();
        for (ChecklistItem ch:checklistItems
             ) {
            if(ch.getTypeCheck() != CheckType.API_CHECK) {
                SshExecute sshExecute = new SshExecute();
                List a=new ArrayList();
                a.add(ch);
                List<ResultItem> b= sshExecute.handle(ch.getServer(),a);
                c.addAll(b);
            }
        }
        return c;
    }

    private List<ResultItem> executeThread(List<List<ChecklistItem>> checklistItemsList) {
        List<ResultItem> resultItemList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(checklistItemsList.size());
        for (List<ChecklistItem> checklistItems : checklistItemsList
        ) {
            Thread thread = new Thread(() -> {
                ChecklistItem standardItem = checklistItems.get(0);
                ExecuteHandle executeHandle = null;
                ServerInfo serverInfo = standardItem.getServer();
                System.out.println("SERVER STANDARD "+serverInfo);
                if (standardItem.getTypeCheck() == CheckType.SERVER_CHECK) {
                    executeHandle = new SshExecute();
                    System.out.println("SERVER");
                } else if (standardItem.getTypeCheck() == CheckType.API_CHECK) {
                    executeHandle = new RestApiExecute();
                    System.out.println("API");
                }
                List<ResultItem> resultItemListExecuted = executeHandle.handle(serverInfo, checklistItems);
                System.out.println("Running");
                System.out.println("============>>>>>>>>>>>>>>>> "+resultItemListExecuted);
                synchronized (resultItemList) {
                    if(resultItemListExecuted!=null)
                        resultItemList.addAll(resultItemListExecuted);
                }
                countDownLatch.countDown();
            });
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SAUUUUUUUUUUUUUUUUUUUUUUUUU" + countDownLatch.getCount());
        System.out.println(resultItemList.toArray());
        System.out.println("ALL DONW");
        return resultItemList;
    }
}
