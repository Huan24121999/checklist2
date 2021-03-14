package viettel.huannt14.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultItem {
    private Integer itemId;

    private Boolean isPassed;

    private String detail;
}