package com.github.typingtanuki.locomotive.widgets.support;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.utils.ProcessFailedException;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.typingtanuki.locomotive.components.TerminalComponent.nullTerminal;

public class ArchitectureSupportWidget extends AbstractWidget {
    private final CountDownLatch latch;
    private final AtomicBoolean isInstalled;

    public ArchitectureSupportWidget(CountDownLatch latch, AtomicBoolean isInstalled) {
        super(I18n.get("enable32bit.title"), I18n.get("enable32bit.description"));

        this.latch = latch;
        this.isInstalled = isInstalled;
        CoreExecutor.execute(this::checkState);
    }

    private void checkState() {
        try {
            if (isArchitectureEnabled()) {
                setState(WidgetState.INSTALLED);
                isInstalled.set(true);
            } else {
                setState(WidgetState.MISSING);
                isInstalled.set(false);
            }
        } catch (ProcessFailedException e) {
            setState(WidgetState.FAILED);
        }
        latch.countDown();
    }

    private boolean isArchitectureEnabled() throws ProcessFailedException {
        ProcessExec processExec = ProcessExec.exec(nullTerminal(), "dpkg", "--print-foreign-architectures");
        return processExec.getStdout().contains("i386");
    }
}
