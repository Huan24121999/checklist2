package viettel.huannt14.checklist.common;


// group all command lines by server, create a thread for a server then run command lines on each server
// all data will send a same static variable

import viettel.huannt14.checklist.service.CompareData;
import viettel.huannt14.checklist.service_imp.ResultComparison;

public class Test {
    public static void main(String args[]) {
        CompareData compareData = new ResultComparison();
        System.out.println(compareData.compare("12.1","12",">="));
    }
}
