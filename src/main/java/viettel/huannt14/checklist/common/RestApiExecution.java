package viettel.huannt14.checklist.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import viettel.huannt14.checklist.entity.*;
import viettel.huannt14.checklist.service.CompareData;
import viettel.huannt14.checklist.service_imp.ResultComparison;

import java.util.ArrayList;
import java.util.List;

/**
 * to handle execution which is Rest API to request the other server
 *
 * @author huannt14
 */
public class RestApiExecution implements HandleExecution {

    private static final Logger logger= LoggerFactory.getLogger(RestApiExecution.class);

    /**
     * execute list checklist item and return result through REST API
     *
     * @param serverInfo server to execute
     * @param checklistItems list checklist item to execute
     * @return list of result of each checklist item
     */
    @Override
    public List<ResultItem> handle(ServerInfo serverInfo, List<ChecklistItem> checklistItems) {
        List<ResultItem> resultItemList = new ArrayList<>();
        for (ChecklistItem ch : checklistItems
        ) {
            ResultItem resultItem = new ResultItem();
            resultItem.setName(ch.getName());
            resultItem.setIsPassed(false);
            resultItem.setGroupCheck(ch.getChecklistGroup().getName());
            resultItem.setRequiredResult(ch.getValuePass());
            try {
                RestTemplate restTemplate = new RestTemplate();
                String url = ch.getAction();
                int typeComparison = ch.getTypeCheck();

                if (typeComparison == CheckType.API_BOOLEAN_CHECK) {
                    RestBooleanResponse result = restTemplate.getForObject(url, RestBooleanResponse.class);
                    if (result.getPassed()) {
                        resultItem.setIsPassed(true);
                    }
                    resultItem.setResult(result.getDescribe());
                } else if (typeComparison == CheckType.API_DATA_CHECK) {
                    RestDataResponse result = restTemplate.getForObject(url, RestDataResponse.class);
                    CompareData compareData = new ResultComparison();
                    Boolean isPassed = compareData.compare(result.getData(), ch.getValuePass(), ch.getOperator());
                    if (isPassed == true) {
                        resultItem.setIsPassed(true);
                    }
                    resultItem.setResult(result.getData());
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                resultItem.setResult(ex.getMessage());
            }
            resultItemList.add(resultItem);
        }
        return resultItemList;
    }

}

