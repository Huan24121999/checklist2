package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.huannt14.checklist.service.ServerInfoService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.jar.JarEntry;

@RestController
@RequestMapping("/api/v1/private/server-info")
public class ServerInfoController {

    @Autowired
    private ServerInfoService serverInfoService;


}
