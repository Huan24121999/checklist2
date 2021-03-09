package viettel.huannt14.checklist.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
