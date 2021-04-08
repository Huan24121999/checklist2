package viettel.huannt14.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response's format for Rest API to check by boolean check
 *
 * @author huannt14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestBooleanResponse {
    private Boolean passed;
    private String describe;
}
