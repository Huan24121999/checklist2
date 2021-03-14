package viettel.huannt14.checklist.common;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(5);
    public static void main(String[] args) {
        Thread run1 = new Thread(() -> {
            System.out.println("Thread 1 is working...");
            try {
                Thread.sleep(5000);
                COUNT_DOWN_LATCH.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 - Finished");
        });
        run1.start();
        Thread run2 = new Thread(() -> {
            System.out.println("Thread 2 is working...");
            try {
                Thread.sleep(4000);
                COUNT_DOWN_LATCH.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 2 - Finished");
        });
        run2.start();
        Thread run3 = new Thread(() -> {
            System.out.println("Thread 3 is working...");
            try {
                Thread.sleep(3000);
                COUNT_DOWN_LATCH.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 3 - Finished");
        });
        run3.start();
        Thread run4 = new Thread(() -> {
            System.out.println("Thread 4 is working...");
            try {
                Thread.sleep(2000);
                COUNT_DOWN_LATCH.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 4 - Finished");
        });
        run4.start();
        Thread run5 = new Thread(() -> {
            System.out.println("Thread 5 is working...");
            try {
                Thread.sleep(1000);
                COUNT_DOWN_LATCH.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 5 - Finished");
        });
        run5.start();
        System.out.println("ok");
//        run1.start();
//        run2.start();
//        run3.start();
//        run4.start();
//        run5.start();
        try {
            COUNT_DOWN_LATCH.await();
        } catch (InterruptedException e) {
            //Handle when a thread gets interrupted.
        }
        System.out.println("All tasks have finished..");
    }
}