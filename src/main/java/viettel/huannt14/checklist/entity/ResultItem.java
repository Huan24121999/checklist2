package viettel.huannt14.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result of execution, using to store each record of output's each checklist item
 * after execution
 *
 * @author huannt14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultItem {

    private String name;
    
    private String groupCheck;
    
    private Boolean isPassed;

    private String requiredResult;
    
    private String result;
}
