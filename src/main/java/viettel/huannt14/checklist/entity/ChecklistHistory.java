package viettel.huannt14.checklist.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * History of each execution
 *
 * @author huannt14
 */
@Entity
@Data
@Table(name = "checklist_history")
public class ChecklistHistory {

    private static final Long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String  result;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    private String detail;

    private String username;
}
