package viettel.huannt14.checklist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import viettel.huannt14.checklist.service.Abc;

@RestController
@RequestMapping("/abc")
public class AbcController {
    @Autowired
    private Abc acb;

    @GetMapping("/abc")
    public int getA(){
        return acb.change();
    }
}
