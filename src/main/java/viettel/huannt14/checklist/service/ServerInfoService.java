package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ServerInfo;

import java.util.List;

public interface ServerInfoService {
    List<ServerInfo> getAll();

    Boolean deleteById(Long serverId);

    ServerInfo save(ServerInfo serverInfo);


}
