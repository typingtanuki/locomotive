package com.github.typingtanuki.locomotive.widgets.support;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArchitectureSupportWidget extends AbstractWidget {
    private final CountDownLatch latch;
    private final AtomicBoolean isInstalled;

    public ArchitectureSupportWidget(CountDownLatch latch, AtomicBoolean isInstalled) {
        super(I18n.get("step.enable32bitstep.title"),
                I18n.get("step.enable32bitstep.description"));
        this.latch = latch;
        this.isInstalled = isInstalled;
        CoreExecutor.execute(this::checkState);
    }

    private void checkState() {
        // TBD
        setState(WidgetState.INSTALLED);
        isInstalled.set(true);
        latch.countDown();
    }
}
