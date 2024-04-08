package com.testclass.concurrency;

import java.util.Vector;

public class WaitDemo2 {

    public static void main(String[] args) {
//初始化任务队列
        TaskQueue taskQueue = new TaskQueue();

        //启动任务consumer
        for (int i = 0; i < 4; i++) {
            new Thread(new Consumer(taskQueue)).start();
        }

        //休眠一段时间等到consumer都启动好
        sleep(2000);

        //启动任务生产者Producer
        new Thread(new Producer(taskQueue)).start();
    }

    private static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// 生产者
class Producer implements Runnable {
    private final TaskQueue taskQueue;

    public Producer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        while (true) {
        generateTask();
        sleep(2000);
        }
    }

    private void generateTask() {
        int taskNum  = (int)(Math.random() * 5+1);
        long timestamp = System.currentTimeMillis();
        for (int i = 0; i < taskNum; i++) {
            String task = "Task_" + timestamp + "_" + i;
            taskQueue.addTask(task);
        }
    }
    private static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//任务消费者
class Consumer implements Runnable {

    private TaskQueue taskQueue;

    public Consumer(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public void run() {
        execTask();
    }

    private void execTask() {
        while (true) {
            //获取任务，如果获取不到，会进入wait队列
            String task = taskQueue.removeTask();
            //任务不为null则模拟执行
            if (null != task) {
                System.out.println(task + " be done. Caller is " + Thread.currentThread().getName());
            }
        }
    }
}

// 任务队列
class TaskQueue {
    private final Vector<String> taskVector = new Vector<>();

    // 添加任务
    public synchronized void addTask(String task) {
        System.out.println(task + " has generated.");
        taskVector.add(task);
        this.notify();
    }

    //移除任务
    public synchronized String removeTask() {
        if (!taskVector.isEmpty()) {
            return taskVector.remove(0);
        } else {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting...");
                //没有任务则进入等待队列
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
