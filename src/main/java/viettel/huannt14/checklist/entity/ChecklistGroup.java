package viettel.huannt14.checklist.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "checklist_group")
public class ChecklistGroup {

    private static final Long serialVersionUID=1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

    @Column(name = "is_check")
    private Boolean isCheck;
}
