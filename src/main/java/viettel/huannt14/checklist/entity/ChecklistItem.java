package viettel.huannt14.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Checklist item (Test case) to check
 *
 * @author huannt14
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "checklist_item")
public class ChecklistItem {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "type_check")
    private Integer typeCheck;

    private String action;

    @Column(name = "value_pass")
    private String valuePass;

    private String operator;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private ChecklistGroup checklistGroup;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "server_id")
    private ServerInfo server;
}
