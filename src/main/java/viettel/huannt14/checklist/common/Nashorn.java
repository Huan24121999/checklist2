package viettel.huannt14.checklist.common;

import javax.script.*;
import java.util.HashMap;
import java.util.Map;

public class Nashorn {

    private final static ScriptEngineManager MANAGER = new ScriptEngineManager();

    public static void main(String[] args) throws ScriptException {
        new Nashorn().testBindingsAsArgument();
        new Nashorn().testScopeBindings("ENGINE_SCOPE", ScriptContext.ENGINE_SCOPE);
        new Nashorn().testScopeBindings("GLOBAL_SCOPE", ScriptContext.GLOBAL_SCOPE);
    }

    private ScriptEngine engine = MANAGER.getEngineByName("nashorn");
    private Map<String, Object> map = new HashMap<>();
    private Bindings bindings = new SimpleBindings(map);

    private Nashorn() {
        map.put("a", 10);
        map.put("b", 20);
    }

    private void testBindingsAsArgument() throws ScriptException {
        System.out.println("Bindings as argument:");
        engine.eval("c = a + b; a += b", bindings);
        System.out.println("map = " + map);
        System.out.println("eval('c', bindings) = " + engine.eval("c", bindings));
        System.out.println("eval('a', bindings) = " + engine.eval("a", bindings));
    }

    private void testScopeBindings(String scope_name, int scope) throws ScriptException {
        System.out.println("\n" + scope_name + ":");
        engine.getContext().setBindings(bindings, scope);
        engine.eval("c = a + b; a += b");
        System.out.println("map = " + map);
        System.out.println("eval('c') = " + engine.eval("c"));
        System.out.println("eval('a') = " + engine.eval("a"));
    }
}