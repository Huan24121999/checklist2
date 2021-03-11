package viettel.huannt14.checklist.common;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ThreadSsh extends Thread{

    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Thread running "+ this.getName());
        String host=this.host;
        String user="root";
        String password="123";
        String command1="df -h | grep -w '/dev/sda1'| awk '{print $(NF-1)}' | rev | cut -c 2-| rev";
        String command2="df";

        /*try {
            listFolderStructure(user,password,host,22,command2);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        try{

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session=jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig(config);
            LocalDateTime now1 = LocalDateTime.now();
            System.out.println(this.getName() + dtf.format(now1));
            session.connect();


            System.out.println("Connected");

            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command2);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();
            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        }catch(Exception e){
             LocalDateTime now1 = LocalDateTime.now();
            System.out.println(this.getName() + dtf.format(now1));

            System.out.println("TIME OUT "+ this.getName());
            e.printStackTrace();
        }
    }
}
