package com.github.typingtanuki.locomotive.widgets.support;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.PpaTester;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class PpaSupportWidget extends AbstractWidget {
    private final CountDownLatch latch;
    private final AtomicBoolean isInstalled;
    private final Ppa ppa;

    public PpaSupportWidget(Ppa ppa, CountDownLatch latch, AtomicBoolean isInstalled) {
        super(ppa.getTitle(), ppa.getDescription());
        this.ppa = ppa;
        this.latch = latch;
        this.isInstalled = isInstalled;

        CoreExecutor.execute(this::checkState);
    }

    private void checkState() {
        try {
            if (PpaTester.isPpaActivated(ppa)) {
                setState(WidgetState.INSTALLED);
                isInstalled.set(true);
            } else {
                setState(WidgetState.MISSING);
                isInstalled.set(false);
            }
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
        latch.countDown();
    }
}
