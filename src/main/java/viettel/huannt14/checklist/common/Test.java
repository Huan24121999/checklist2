package viettel.huannt14.checklist.common;


// group all command lines by server, create a thread for a server then run command lines on each server
// all data will send a same static variable

import viettel.huannt14.checklist.entity.ChecklistGroup;
import viettel.huannt14.checklist.entity.ChecklistItem;
import viettel.huannt14.checklist.entity.ResultItem;
import viettel.huannt14.checklist.entity.ServerInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[]) {

       String e=" java.net.ConnectException: Connection refused: connect";
       String[] a= e.split("Exception:");
        System.out.println(a[0]);
        System.out.println(a[1]);
    }
}
