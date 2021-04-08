package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viettel.huannt14.checklist.entity.ServerInfo;
import viettel.huannt14.checklist.repository.ServerInfoRepo;
import viettel.huannt14.checklist.service.ServerInfoService;

import java.util.List;

/**
 * Implementation of ServerInfoService
 *
 * @author huannt14
 */
@Service
public class ServerInfoServiceImpl implements ServerInfoService {

    private static final Logger logger= LoggerFactory.getLogger(ServerInfoServiceImpl.class);

    @Autowired
    private ServerInfoRepo serverInfoRepo;

    /**
     * delete a server by id's server
     *
     * @param serverId id's server
     * @return true if deleted, otherwise return false
     */
    @Override
    public Boolean deleteById(Integer serverId) {
        try{
            serverInfoRepo.deleteById(serverId);
            return true;
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return false;
        }
    }

    /**
     * save a server to db
     *
     * @param serverInfo server needed to save
     * @return stored server
     */
    @Override
    public ServerInfo save(ServerInfo serverInfo) {
        try{
            return serverInfoRepo.save(serverInfo);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * find all servers
     *
     * @return list of servers
     */
    @Override
    public List<ServerInfo> findAll() {
        try{
            return serverInfoRepo.findAll();
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return null;
        }
    }
}
