package viettel.huannt14.checklist.common;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;
import viettel.huannt14.checklist.service.CompareData;
import viettel.huannt14.checklist.service_imp.ResultComparison;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * to handle execution which is Command line
 *
 * @author huannt14
 */
public class SshExecution implements HandleExecution {

    private static final Logger logger = LoggerFactory.getLogger(SshExecution.class);

    Session session = null;

    /**
     * execute list checklist item and return result through Command line
     *
     * @param serverInfo     server to execute
     * @param checklistItems list checklist item to execute
     * @return list of result of each checklist item
     */
    public List<ResultItem> handle(ServerInfo serverInfo, List<ChecklistItem> checklistItems) {
        CompareData compareData = new ResultComparison();

        List<ResultItem> resultItemList = new ArrayList<>();
        // connect to server
        try {
            connectSsh(serverInfo.getIpAddress(), serverInfo.getUsername(), serverInfo.getPassword());
            for (ChecklistItem checklistItem : checklistItems
            ) {
                // init default result item
                ResultItem resultItem = new ResultItem();
                resultItem.setName(checklistItem.getName());
                resultItem.setRequiredResult(checklistItem.getValuePass());
                resultItem.setGroupCheck(checklistItem.getChecklistGroup().getName());
                resultItem.setIsPassed(false);
                try {
                    String ExecutedResult = executeCommand(checklistItem.getAction());
                    if (ExecutedResult == null && checklistItem.getValuePass() == null) {
                        resultItem.setIsPassed(true);
                    } else if (ExecutedResult == null && checklistItem.getValuePass() != null) {
                        resultItem.setResult("Empty");
                    } else if (ExecutedResult.startsWith("Error")) {
                        resultItem.setResult(ExecutedResult);
                    } else {
                        // compare executed result to required value
                        Boolean checkCompare = compareData.compare(ExecutedResult, checklistItem.getValuePass(), checklistItem.getOperator());
                        if (checkCompare) {
                            resultItem.setIsPassed(true);
                        }
                        resultItem.setResult(ExecutedResult);
                    }
                } catch (JSchException e) {
                    //e.printStackTrace();
                    // set result value of execution is the cause of exception
                    resultItem.setResult("Error:" + e.getMessage().split("Exception:")[1]);
                    logger.error(e.getMessage());
                } catch (IOException e) {
                    //e.printStackTrace();
                    // set result value of execution is the cause of exception
                    resultItem.setResult("Error:" + e.getMessage().split("Exception:")[1]);
                    logger.error(e.getMessage());
                }
                resultItemList.add(resultItem);
            }
        } catch (JSchException e) {
//            e.printStackTrace();
            for (ChecklistItem checklistItem : checklistItems
            ) {
                ResultItem resultItem = new ResultItem();
                resultItem.setName(checklistItem.getName());
                resultItem.setGroupCheck(checklistItem.getChecklistGroup().getName());
                resultItem.setRequiredResult(checklistItem.getValuePass());
                resultItem.setIsPassed(false);
                resultItem.setResult("Error: " + e.getMessage().split("Exception:")[1]);
                resultItemList.add(resultItem);
            }
            logger.error(e.getMessage());
        }
        disconnectSsh();
        return resultItemList;
    }

    /**
     * create ssh connection to server which needed to execute command line
     *
     * @param host ip address
     * @param username username
     * @param password password
     * @throws JSchException
     */
    private void connectSsh(String host, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, 22);
        session.setPassword(password);
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        //session.setTimeout(10000);
        session.connect();
    }

    /**
     * disconnect ssh connection
     */
    private void disconnectSsh() {
        if (session != null)
            session.disconnect();
    }

    /**
     * execute command
     * @param script command line needed to execute
     * @return output of execution
     * @throws JSchException
     * @throws IOException
     */
    private String executeCommand(String script) throws JSchException, IOException {
        String result = null;

        // create a channel for a command line
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        (channel).setCommand(script);

        // create InputStream to read output value return from execution
        InputStream in = channel.getInputStream();
        channel.setErrStream(System.err);
        channel.connect();
        try {
            result = readResponseData(in, channel);
        } catch (Exception e) {
            //e.printStackTrace();
            result = "Error: " + e.getMessage();
            logger.error(e.getMessage());
        }
        channel.disconnect();
        return result;
    }

    /**
     * read output value return from execution
     *
     * @param input
     * @param channel
     * @return
     * @throws Exception
     */
    private String readResponseData(InputStream input, Channel channel) throws Exception {
        String value = null;
        int SIZE = 1024;
        byte[] tmp = new byte[SIZE];
        while (true) {
            while (input.available() > 0) {
                int i = input.read(tmp, 0, SIZE);
                if (i < 0)
                    break;
                value = new String(tmp, 0, i);
            }
            if (channel.isClosed()) {
                break;
            }
        }
        return value;
    }

}
