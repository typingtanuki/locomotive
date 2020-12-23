package com.github.typingtanuki.locomotive.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class CoreExecutor {
    private static ThreadPoolExecutor executor;

    private CoreExecutor() {
        super();
    }

    public static synchronized void init() {
        if (executor != null) {
            return;
        }

        executor = new ThreadPoolExecutor(
                10,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20));
    }

    public static void execute(Runnable runnable) {
        executor.submit(runnable);
    }
}
