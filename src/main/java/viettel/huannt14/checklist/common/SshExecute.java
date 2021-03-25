package viettel.huannt14.checklist.common;

import com.jcraft.jsch.*;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;
import viettel.huannt14.checklist.service.CompareData;
import viettel.huannt14.checklist.service_imp.CompareString;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SshExecute implements ExecuteHandle {
    Session session = null;

    //to execute for a group checklist item on the same server
    public List<ResultItem> handle(ServerInfo serverInfo, List<ChecklistItem> checklistItems) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(serverInfo.getName()+ "Handle: ============================" +dtf.format(now1));

        CompareData compareData = new CompareString();

        List<ResultItem> resultItemList = new ArrayList<>();
        // connect to server
        try {
            connectSsh(serverInfo.getIpAddress(), serverInfo.getUsername(), serverInfo.getPassword());
            for (ChecklistItem checklistItem : checklistItems
            ) {
                ResultItem resultItem = new ResultItem();
                resultItem.setName(checklistItem.getName());
                resultItem.setRequiredResult(checklistItem.getValuePass());
                resultItem.setGroupCheck(checklistItem.getChecklistGroup().getName());
                resultItem.setIsPassed(false);
                try {
                    String resultExecuted = executeCommand(checklistItem.getAction());
                    if (resultExecuted == null && checklistItem.getValuePass() == null) {
                        resultItem.setIsPassed(true);
                    } else if (resultExecuted == null && checklistItem.getValuePass() != null) {
                        resultItem.setResult("Empty");
                    } else if (resultExecuted.startsWith("Error")) {
                        resultItem.setResult(resultExecuted);
                    } else {
                        Boolean checkCompare = compareData.compare(resultExecuted, checklistItem.getValuePass());
                        if (checkCompare) {
                            resultItem.setIsPassed(true);
                        }
                        resultItem.setResult(resultExecuted);
                    }
                } catch (JSchException e) {
                    //e.printStackTrace();
                    resultItem.setResult("Error:" + e.getMessage().split("Exception:")[1]);
                } catch (IOException e) {
                    //e.printStackTrace();
                    resultItem.setResult("Error:" + e.getMessage().split("Exception:")[1]);
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
        }
        disconnectSsh();

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(serverInfo.getName()+"Handle finish: ============================"+dtf.format(now2));

        return resultItemList;
    }

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

    private void disconnectSsh() {
        if (session != null)
            session.disconnect();
    }

    // create a channel for a command line
    private String executeCommand(String script) throws JSchException, IOException {
        String result = null;
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        (channel).setCommand(script);

        InputStream in = channel.getInputStream();
        channel.setErrStream(System.err);
        channel.connect();
        try {
            result = readResponseData(in, channel);
        } catch (Exception e) {
            //e.printStackTrace();
            result = "Error: " + e.getMessage();
        }
        channel.disconnect();
        return result;
    }

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
