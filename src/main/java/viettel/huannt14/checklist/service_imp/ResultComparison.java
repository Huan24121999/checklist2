package viettel.huannt14.checklist.service_imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import viettel.huannt14.checklist.service.CompareData;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Pattern;

/**
 * compare executed value from Command Execution to the required value
 *
 * @author huannt14
 */
public class ResultComparison implements CompareData<String> {

    private static final Logger logger = LoggerFactory.getLogger(ResultComparison.class);

    private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    /**
     * compare executed value from Command Execution to the required value
     *
     * @param result    executed result
     * @param valuePass required value to pass
     * @param operator  operator to compare
     * @return true if satisfy, otherwise return false
     */
    @Override
    public Boolean compare(String result, String valuePass, String operator) {
        //init engine to execute script ( script is likely script language: JavaScript)
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        boolean check = false;
        try {
            // check if valuePass is a number?
            boolean isNumericValuePass = pattern.matcher(valuePass).matches();
            // check if result is a number?
            boolean isNumericResult = pattern.matcher(result).matches();

            if (isNumericResult && isNumericValuePass) {
                if (operator == null || operator.equals("=")) {
                    operator = "==";
                }
                check = Boolean.parseBoolean(engine.eval(result + operator + valuePass).toString());
            } else if (isNumericResult == false && isNumericValuePass == false) {
                check = result.equals(valuePass);
            }

        } catch (ScriptException e) {
            logger.error(e.getMessage());
        }
        return check;
    }
}
