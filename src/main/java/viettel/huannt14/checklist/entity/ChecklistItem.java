package viettel.huannt14.checklist.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "checklist_item")
public class ChecklistItem {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "type_check")
    private String typeCheck;

    private String action;

    @Column(name = "value_pass")
    private String valuePass;

    @Column(name = "server_check")
    private String serverCheck;

    @Column(name = "is_check")
    private Boolean isCheck;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private ChecklistGroup checklistGroup;
}
