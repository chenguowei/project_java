package com.testclass.concurrency;

public class ThreadDemo extends Thread {
    public  static  void main(String[] args) {
        new ThreadDemo().start();
    }

    @Override public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
