package com.testclass.concurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    String other = exgr.exchange(A);
                    System.out.println("银行流水exchange value: " + other);
                } catch (InterruptedException e) {

                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String A = exgr.exchange("B");
                    System.out.println("A 和 B 数据是否一致" + A.equals(B) + ", A 录入的是：" + A + ", B录入是：" + B);
                } catch (InterruptedException e){

                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String C = "银行流水C";
                    String other = exgr.exchange("C");
                    System.out.println("银行流水C exchanger value: " + other);
                } catch (InterruptedException e){

                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String D = "银行流水D";
                    String other = exgr.exchange("D");
                    System.out.println("银行流水D exchanger value: " + other);
                } catch (InterruptedException e){

                }
            }
        });

        threadPool.shutdown();
    }
}
