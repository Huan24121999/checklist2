package viettel.huannt14.checklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viettel.huannt14.checklist.entity.ServerInfo;

@Repository
public interface ServerInfoRepo extends JpaRepository<ServerInfo,Integer> {
}
