package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ServerInfo;
import viettel.huannt14.checklist.service.ServerInfoService;

import java.util.Optional;

/**
 * controller handle http request regarding to Server
 *
 * @author huannt14
 */
@RestController
@RequestMapping("/api/v1/private/server-info")
public class ServerInfoController {

    @Autowired
    private ServerInfoService serverInfoService;

    /**
     * upload new Server
     *
     * @param serverInfo server needed to upload
     * @return upload server
     */
    @PostMapping("upload")
    public ResponseEntity<JsonResult> upload(@RequestBody ServerInfo serverInfo){
        return Optional.ofNullable(serverInfoService.save(serverInfo))
                .map(serverInfoResult->{
                    return JsonResult.uploaded(serverInfoResult);
                })
                .orElse(JsonResult.saveError("ServerInfo"));

    }

    /**
     * update a server
     *
     * @param serverInfo server needed to update
     * @return updated server
     */
    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@RequestBody ServerInfo serverInfo){
        return Optional.ofNullable(serverInfoService.save(serverInfo))
                .map(serverInfoResult->{
                    return JsonResult.updated(serverInfoResult);
                })
                .orElse(JsonResult.saveError("ServerInfo"));

    }

    /**
     * delete a server by id's server
     *
     * @param id id's server
     * @return deleted= true/false
     */
    @DeleteMapping("/delete")
    public ResponseEntity<JsonResult> delete(@RequestParam(name = "id") Integer id){
        Boolean deleted= serverInfoService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ServerInfo");
    }

    /**
     * find all servers
     *
     * @return list of servers
     */
    @GetMapping("find-all")
    public ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(serverInfoService.findAll())
                .map(serverInfos -> {
                    return JsonResult.found(serverInfos);
                })
                .orElse(JsonResult.notFound("ServerInfo"));
    }


}
