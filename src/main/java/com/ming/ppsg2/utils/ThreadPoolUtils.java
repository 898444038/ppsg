package com.ming.ppsg2.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池处理流程
 * 核心线程池里线程是否都在执行任务。如果不是，则创建一个新的工作线程来执行任务。
 * 线程池工作队列是否已满。如果工作队列没有满，则把新提交任务存储在这个这个工作队列。
 * 线程池的线程是否都处于工作状态。如果没有，创建一个新的工作线程来执行任务。
 * 饱和策略处理任务。
 */
public final class ThreadPoolUtils {

    //创建固定线程数的线程池
    private static final ExecutorService many_pool = Executors.newFixedThreadPool(2);
    private static ExecutorService many_size_pool = null;
    //创建单个线程数的线程池
    //private static final ExecutorService single_pool = Executors.newSingleThreadExecutor();
    //大小无界的线程池，适用于执行很多的短期异步任务
    //private static final ExecutorService cached_pool = Executors.newCachedThreadPool();
    //适合于多个后台线程执行周期任务
    //private static final ExecutorService scheduled_pool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());


    /**
     * 获取线程池实例，便于统一切换不同的线程池实例
     */
    private static ExecutorService getExecutor() {
        return many_pool;
    }
    private static ExecutorService getExecutor(int size) {
        many_size_pool = Executors.newFixedThreadPool(size);
        return many_size_pool;
    }

    /*private static ExecutorService getExecutor() {
        return single_pool;
    }
    private static ExecutorService getExecutor() {
        return cached_pool;
    }
    private static ExecutorService getExecutor() {
        return scheduled_pool;
    }*/
    /**
     * 提交任务给线程池执行
     */
    public static void execute(Runnable task) {
        getExecutor().execute(task);
    }
    public static void execute(Runnable task,int size) {
        getExecutor(size).execute(task);
    }
}