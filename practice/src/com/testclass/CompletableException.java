package com.testclass;

import java.util.concurrent.CompletableFuture;

public class CompletableException {
    static CompletableFuture<Breakable> test(String id, int failcount) {
        return CompletableFuture.completedFuture(new Breakable(id, failcount))
                .thenApply(Breakable::work)
                .thenApply(Breakable::work)
                .thenApply(Breakable::work)
                .thenApply(Breakable::work);
    }

    public static void main(String[] args) {
        test("A", 1);
        test("B", 2);
        test("C", 3);
        test("D", 4);
        test("E", 5);

        try {
            test("F", 2).get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(test("H",2).isDone());

        CompletableFuture<Integer> cfi = new CompletableFuture<>();
        System.out.println("done?" + cfi.isDone());

        cfi.completeExceptionally(new RuntimeException("forced"));

        try {
            cfi.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}
