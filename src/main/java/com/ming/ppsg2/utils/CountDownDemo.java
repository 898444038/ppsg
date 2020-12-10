package com.ming.ppsg2.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownDemo {
    public static class T extends Thread {
        //休眠时间（秒）
        int sleepSeconds;
        CountDownLatch countDownLatch;

        public T(String name, int sleepSeconds, CountDownLatch countDownLatch) {
            super(name);
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Thread ct = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + ct.getName() + ",开始处理!");
            try {
                //模拟耗时操作，休眠sleepSeconds秒
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + ct.getName() + ",处理完毕,耗时:" + (endTime - startTime));
        }
    }


    public static void main(String[] args) throws InterruptedException {
        int count = Runtime.getRuntime().availableProcessors()+1;
        //System.out.println("可用线程数"+Runtime.getRuntime().availableProcessors());
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start!");
        CountDownLatch countDownLatch = new CountDownLatch(count);
        long starTime = System.currentTimeMillis();

        for (int i=0;i<count;i++){
            T t1 = new T("解析sheet"+i+"线程", 2+i, countDownLatch);
            t1.start();
        }
        /*T t1 = new T("解析sheet1线程", 2, countDownLatch);
        t1.start();

        T t2 = new T("解析sheet2线程", 4, countDownLatch);
        t2.start();

        T t3 = new T("解析sheet3线程", 5, countDownLatch);
        t3.start();*/

        countDownLatch.await();

        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 end!");

        long endTime = System.currentTimeMillis();
        System.out.println("总耗时:" + (endTime - starTime));
    }
}
