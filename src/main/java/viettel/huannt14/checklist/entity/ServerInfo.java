package viettel.huannt14.checklist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "server_info")
public class ServerInfo {

    private static final Long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "ip_address")
    private String ipAddress;

    private String username;

    private String password;
}
