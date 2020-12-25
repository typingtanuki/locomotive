package com.github.typingtanuki.locomotive.widgets.support;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.utils.PackageTester;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class BinarySupportWidget extends AbstractWidget {
    private final CountDownLatch latch;
    private final AtomicBoolean isInstalled;
    private final Binary binary;

    public BinarySupportWidget(Binary binary, CountDownLatch latch, AtomicBoolean isInstalled) {
        super(binary.getTitle(), binary.getDescription());

        this.binary = binary;
        this.latch = latch;
        this.isInstalled = isInstalled;

        CoreExecutor.execute(this::checkState);
    }

    private void checkState() {
        if (PackageTester.isBinaryOnPath(binary)) {
            setState(WidgetState.INSTALLED);
            isInstalled.set(true);
        } else {
            setState(WidgetState.MISSING);
            isInstalled.set(false);
        }
        latch.countDown();
    }
}
