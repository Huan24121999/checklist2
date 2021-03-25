package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import viettel.huannt14.checklist.common.JsonResult;
import viettel.huannt14.checklist.entity.ServerInfo;
import viettel.huannt14.checklist.service.ServerInfoService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/server-info")
public class ServerInfoController {

    @Autowired
    private ServerInfoService serverInfoService;

    @PostMapping("upload")
    public ResponseEntity<JsonResult> upload(@RequestBody ServerInfo serverInfo){
        return Optional.ofNullable(serverInfoService.save(serverInfo))
                .map(serverInfoResult->{
                    return JsonResult.uploaded(serverInfoResult);
                })
                .orElse(JsonResult.saveError("ServerInfo"));

    }

    @PutMapping("/update")
    public ResponseEntity<JsonResult> update(@RequestBody ServerInfo serverInfo){
        return Optional.ofNullable(serverInfoService.save(serverInfo))
                .map(serverInfoResult->{
                    return JsonResult.updated(serverInfoResult);
                })
                .orElse(JsonResult.saveError("ServerInfo"));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<JsonResult> delete(@RequestParam(name = "id") Integer id){
        Boolean deleted= serverInfoService.deleteById(id);
        if(deleted)
            return JsonResult.deleted();
        return JsonResult.saveError("ServerInfo");
    }

    @GetMapping("find-all")
    public ResponseEntity<JsonResult> getAll(){
        return Optional.ofNullable(serverInfoService.getAll())
                .map(serverInfos -> {
                    return JsonResult.found(serverInfos);
                })
                .orElse(JsonResult.notFound("ServerInfo"));
    }


}
