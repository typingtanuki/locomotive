package com.github.typingtanuki.locomotive.steps;

import com.github.typingtanuki.locomotive.controller.component.StepState;
import com.github.typingtanuki.locomotive.controller.monitor.Monitor;
import com.github.typingtanuki.locomotive.settings.CommonSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

public abstract class AbstractStep implements Step {
    private final List<Monitor> monitors = new ArrayList<>();

    @Override
    public String description() {
        return "step." + this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".title";
    }

    @Override
    public String title() {
        return "step." + this.getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".description";
    }

    @Override
    public String[] descriptionArgs() {
        return new String[0];
    }

    @Override
    public String[] titleArgs() {
        return new String[0];
    }

    @Override
    public Future<StepState> execute() {
        return CommonSettings.submitTask(this::doExecute);
    }

    protected abstract StepState doExecute();

    public final void addMonitor(Monitor monitor) {
        monitors.add(monitor);
    }

    public final <T extends Monitor> T monitor(Class<? extends T> clazz) {
        for (Monitor monitor : monitors) {
            if (clazz.isAssignableFrom(monitor.getClass())) {
                return (T) monitor;
            }
        }
        throw new IllegalStateException("Could not find monitor of class " + clazz.getSimpleName());
    }
}
