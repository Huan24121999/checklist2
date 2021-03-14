package viettel.huannt14.checklist.common;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SSHUploader {

    Session session = null;

    public SSHUploader() {

    }

    public void connect() {
        try {

            JSch jsch = new JSch();
            session = jsch.getSession("root", "172.16.10.103", 22);
            session.setPassword("123");
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());

        }
    }

    public void executeCommand(String script) throws JSchException, IOException {
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        (channel).setCommand(script);

        InputStream in = channel.getInputStream();
        ((ChannelExec) channel).setErrStream(System.err);

        System.out.println("Chanel Connected");
        channel.connect();
        try {
            printResult(in, channel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

        channel.disconnect();
        System.out.println("Channel disconnect");
    }

    private static void printResult(InputStream input,
                                    Channel channel) throws Exception {
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
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
        }
        System.out.println("Value= " + value);
    }

    public void disconnect() {
        session.disconnect();
    }


    public static void main(String... args) throws JSchException, IOException {

        SSHUploader up = new SSHUploader();
        up.connect();
        String cmd1 = "df -h | grep -w '/dev/sda1' | awk '{print $(NF-1)}' | rev | cut -c 2- | rev";
        String cmd2 = "df -h | grep -eerw '/run/user/02' | awk '{print $(NF-1)}' | rev | cut -c 2- | rev";
        up.executeCommand(cmd1);
        up.executeCommand(cmd2);
        up.executeCommand(cmd1 + ";" + cmd2);
        up.disconnect();
    }

}