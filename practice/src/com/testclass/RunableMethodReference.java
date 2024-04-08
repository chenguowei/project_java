package com.testclass;

class Go {
    static void go() {
        System.out.println("Go::go()");
    }
}

public class RunableMethodReference {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous");
            }
        }
        ).start();

        new Thread(
                ()->System.out.println("lamdba")
        ).start();

        new Thread(Go::go).start();
    }

}
