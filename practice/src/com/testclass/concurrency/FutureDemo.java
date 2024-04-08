package com.testclass.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(new CallableWorker());

        new Thread(task).start();

        try {
            System.out.println(task.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class CallableWorker implements Callable<Integer> {
    public Integer call() throws Exception {
        return 10;
    }
}
