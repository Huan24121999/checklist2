package viettel.huannt14.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Group of Checklist items
 *
 * @author huannt14
 */
@Entity
@Data
@Table(name = "checklist_group")
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistGroup {

    private static final Long serialVersionUID=1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String description;

}
