package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import viettel.huannt14.checklist.service.CompareData;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CompareString implements CompareData<String> {

    private static final Logger logger= LoggerFactory.getLogger(CompareString.class);

    @Override
    public Boolean compare(String result,String valuePass) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
             return Boolean.parseBoolean(engine.eval(result+valuePass).toString());
        } catch (ScriptException e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
