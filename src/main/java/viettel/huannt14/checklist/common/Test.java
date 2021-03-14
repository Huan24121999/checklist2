package viettel.huannt14.checklist.common;


// group all command lines by server, create a thread for a server then run command lines on each server
// all data will send a same static variable

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        ThreadSsh threadSsh1=new ThreadSsh();
        ThreadSsh threadSsh2=new ThreadSsh();
        ThreadSsh threadSsh3=new ThreadSsh();

        threadSsh1.setName("A");
        threadSsh1.setHost("172.16.10.100");
        threadSsh2.setName("B");
        threadSsh2.setHost("172.16.10.101");
        threadSsh3.setName("C");
        threadSsh3.setHost("172.16.10.102");

        threadSsh1.start();
        threadSsh2.start();
        threadSsh3.start();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println("=============================="+dtf.format(now1));
        for(int i=0;i<=2;i++){
           ApiConnection apiConnection=new ApiConnection();
           apiConnection.abc("172.16.10."+(100+i));
        }
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println("==============================="+dtf.format(now2));
    }
}
