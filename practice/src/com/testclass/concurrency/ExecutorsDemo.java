package com.testclass.concurrency;

import java.util.concurrent.*;

public class ExecutorsDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        // 提交匿名Callable
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 11;
            }
        });

        // 获取线程执行返回值
        System.out.println("future:"+future.get());
        ClassLoader classLoader = ExecutorsDemo.class.getClassLoader();
    }
}
