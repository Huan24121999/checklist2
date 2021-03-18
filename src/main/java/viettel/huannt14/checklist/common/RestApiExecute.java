package viettel.huannt14.checklist.common;

import org.springframework.web.client.RestTemplate;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.RestResponse;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class RestApiExecute implements ExecuteHandle{
    @Override
    public List<ResultItem> handle(ServerInfo serverInfo, List<ChecklistItem> checklistItems) {
        List<ResultItem> resultItemList =new ArrayList<>();
        for (ChecklistItem ch:checklistItems
             ) {
            ResultItem resultItem =new ResultItem();
            resultItem.setItemId(ch.getId());
            resultItem.setIsPassed(false);
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = ch.getAction();
                RestResponse result = restTemplate.getForObject(url, RestResponse.class);
                if(result.getPassed())
                    resultItem.setIsPassed(true);
                resultItem.setDetail(result.getDescribe());
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                resultItem.setDetail(ex.getMessage());
            }
            resultItemList.add(resultItem);
        }
        return resultItemList;
    }

}
// url= localhost:8080 /api/v1/....

class Test2{
    public static void main(String[] args) {
        RestApiExecute restApiExecute= new RestApiExecute();
        restApiExecute.handle(null,null);
    }
}
