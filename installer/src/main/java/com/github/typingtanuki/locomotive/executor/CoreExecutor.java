package com.github.typingtanuki.locomotive.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * An executor service to run all background tasks
 */
public final class CoreExecutor {
    /**
     * The executor service itself
     */
    private static ThreadPoolExecutor executor;

    private CoreExecutor() {
        super();
    }

    /**
     * Sets up a new executor service (if not already initialized)
     */
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

    /**
     * Submit a runnable for asynchronous execution
     */
    public static void execute(Runnable runnable) {
        executor.submit(runnable);
    }

    public static void exit() {
        executor.shutdown();
        try {
            executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        executor.shutdownNow();
    }
}
