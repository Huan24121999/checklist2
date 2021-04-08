package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ServerInfo;

import java.util.List;

/**
 * service is provided to interact with Server Records
 *
 * @author huannt14
 */
public interface ServerInfoService {
    Boolean deleteById(Integer serverId);

    ServerInfo save(ServerInfo serverInfo);

    List<ServerInfo> findAll();
}
