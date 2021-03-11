package viettel.huannt14.checklist.common;


// group all command lines by server, create a thread for a server then run command lines on each server
// all data will send a same static variable

public class Test {
    public static void main(String[] args) {
        ThreadSsh threadSsh1=new ThreadSsh();
        ThreadSsh threadSsh2=new ThreadSsh();
        ThreadSsh threadSsh3=new ThreadSsh();
        ThreadSsh threadSsh4=new ThreadSsh();
        ThreadSsh threadSsh5=new ThreadSsh();

        threadSsh1.setName("A");
        threadSsh1.setHost("192.168.1.19");
        threadSsh2.setName("B");
        threadSsh2.setHost("192.168.1.22");
        threadSsh3.setName("C");
        threadSsh3.setHost("192.168.1.25");
        threadSsh4.setName("D");
        threadSsh4.setHost("192.168.1.10");
        threadSsh5.setName("E");
        threadSsh5.setHost("192.168.1.99");

        threadSsh1.start();
        threadSsh2.start();
        threadSsh3.start();
        threadSsh4.start();
        threadSsh5.start();

        for(int i=1;i<=5;i++){
           ApiConnection apiConnection=new ApiConnection();
           apiConnection.abc();
        }
    }
}
