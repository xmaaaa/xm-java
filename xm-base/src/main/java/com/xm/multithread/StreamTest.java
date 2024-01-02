package com.xm.multithread;

import com.google.common.collect.Lists;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hongwan
 * @date 2023/2/23
 */
public class StreamTest {


    public static void main(String[] args) {
        testVector();
        testSynchronizedList();
        testCopyOnWriteArrayList();
        //testList();
        //autoCloseTest();
    }

    public static void autoCloseTest() {
        try (AutoCloseAbleBean autoCloseable = new AutoCloseAbleBean()) {
            CompletableFuture.runAsync(() -> runAsync(autoCloseable));
            throw new RuntimeException("test");
//            autoCloseable.dosomething();
//            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }

    public static void runAsync(AutoCloseAbleBean autoCloseable) {
        autoCloseable.dosomething();
    }


    public static void testVector() {
        List<Integer> vector = new Vector<>();
        long time1 = System.currentTimeMillis();
        List<CompletableFuture<Void>> async = Lists.newArrayList();
        for (int i = 0; i < 20000; i++) {
            int finalI = i;
            async.add(CompletableFuture.runAsync(() -> vector.add(finalI)));
        }
        CompletableFuture.allOf(async.toArray(new CompletableFuture[0])).join();
        long time2 = System.currentTimeMillis();
        System.out.println("vector: " + (time2 - time1));
    }

    public static void testSynchronizedList() {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        long time1 = System.currentTimeMillis();
        List<CompletableFuture<Void>> async = Lists.newArrayList();
        for (int i = 0; i < 20000; i++) {
            int finalI = i;
            async.add(CompletableFuture.runAsync(() -> list.add(finalI)));
        }
        CompletableFuture.allOf(async.toArray(new CompletableFuture[0])).join();
        long time2 = System.currentTimeMillis();
        System.out.println("synchronizedList: " + (time2 - time1));
    }

    public static void testCopyOnWriteArrayList() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        long time1 = System.currentTimeMillis();
        List<CompletableFuture<Void>> async = Lists.newArrayList();
        for (int i = 0; i < 20000; i++) {
            int finalI = i;
            async.add(CompletableFuture.runAsync(() -> list.add(finalI)));
        }
        CompletableFuture.allOf(async.toArray(new CompletableFuture[0])).join();
        long time2 = System.currentTimeMillis();
        System.out.println("copyOnWriteArrayList: " + (time2 - time1));
    }


    private static void testList() {
        List<Integer> list = Lists.newArrayList();
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> list.add(finalI));
        }
        long time2 = System.currentTimeMillis();
        System.out.println("copyOnWriteArrayList: " + (time2 - time1));
    }


    static class AutoCloseAbleBean implements Closeable {
        @Override
        public void close() {
            System.out.println("i am close");
        }

        void dosomething() {
            System.out.println("before close");
        }
    }
}
