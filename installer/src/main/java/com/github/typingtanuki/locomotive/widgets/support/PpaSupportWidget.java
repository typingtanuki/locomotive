package com.github.typingtanuki.locomotive.widgets.support;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class PpaSupportWidget extends AbstractWidget {
    private final CountDownLatch latch;
    private final AtomicBoolean isInstalled;

    public PpaSupportWidget(String ppa, CountDownLatch latch, AtomicBoolean isInstalled) {
        super(ppa, "qweqwe");
        CoreExecutor.execute(this::checkState);
        this.latch = latch;
        this.isInstalled = isInstalled;
    }

    private void checkState() {
        // TBD
        setState(WidgetState.INSTALLED);
        isInstalled.set(true);
        latch.countDown();
    }
}
