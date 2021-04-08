package viettel.huannt14.checklist.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.springframework.web.client.RestTemplate;

import javax.script.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestScriptEngine {
    public static void main(String[] args) {
        RestTemplate restTemplate= new RestTemplate();
        String result= restTemplate.getForObject("http://localhost:8201/find-by-id?id=1",String.class);
        System.out.println(result);
        ObjectMapper mapper= new ObjectMapper();
        try {
            List<Map<String,String>> mapevent=mapper.readValue(result, new TypeReference<List<Map<String, String>>>() {});
            System.out.println(mapevent);
            mapevent.forEach((temp->{
                System.out.println(temp.get("id"));
            }));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
