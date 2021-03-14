package viettel.huannt14.checklist.common;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestScriptEngine {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        Map<String, Object> map = new HashMap<>();
        Bindings bindings = new SimpleBindings(map);
        map.put("a",10);
        map.put("b",10);

        try {
            System.out.println("Bindings as argument:");
            engine.eval("c = a + b; a += b", bindings);
            System.out.println("map = " + map);
            System.out.println("eval('c', bindings) = " + engine.eval("c", bindings));
            System.out.println("eval('a', bindings) = " + engine.eval("a", bindings));
        } catch (ScriptException e) {
            e.printStackTrace();
        }


        ScriptEngine engine2 = new ScriptEngineManager().getEngineByName("nashorn");

        try {
            String expected="'10'";
            Object result = engine2.eval("10==10");
            System.out.println(result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }

}
