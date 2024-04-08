package com.testclass.concurrency;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExectorDemo {
    public static void main(String[] args) {
        final int QUEUE_CAPACITY = 20;
        BlockingDeque<Runnable> runnableBlockingDeque = new LinkedBlockingDeque<>(QUEUE_CAPACITY);

        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10, 60, TimeUnit.SECONDS, runnableBlockingDeque);

        int taskNo = 0;
        int queueSize = 0;

        while(true) {
            int taskNum = getTaskNum();
            for (int i = 0; i < taskNum; i++) {
                taskNo++;
            }

            if (queueSize < QUEUE_CAPACITY) {
                executor.execute(new Work("Task" + taskNo));
            } else {
                while (true) {
                    sleep(200);
                    queueSize = executor.getQueue().size();
                    if (queueSize < QUEUE_CAPACITY) {
                        executor.execute(new Work("Task" + taskNo));
                        break;
                    }
                }

                queueSize = executor.getQueue().size();
            }
        }
    }

    private static int getTaskNum() {
        return ((int)(1+Math.random()*(8-1+1)));
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Work implements Runnable {
    private String name;

    public Work(String name) {
        this.name = name;
    }

    public void run() {
        exec();
    }

    private void exec() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " be called.");
    }
}