package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viettel.huannt14.checklist.entity.ServerInfo;

public interface ServerInfoRepo extends JpaRepository<ServerInfo,Long> {
}
