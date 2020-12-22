package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.controller.monitor.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class Monitorable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Monitorable.class);

    private final List<Monitor> monitors = new ArrayList<>();

    public final void addMonitor(Monitor monitor) {
        monitors.add(monitor);
    }

    @SuppressWarnings("unchecked")
    protected final <T extends Monitor> T monitor(Class<? extends T> clazz) {
        for (Monitor monitor : monitors) {
            if (clazz.isAssignableFrom(monitor.getClass())) {
                return (T) monitor;
            }
        }
        LOGGER.error("No monitor {} int {}", clazz.getSimpleName(), this.getClass().getSimpleName());
        throw new IllegalStateException("Could not find monitor of class " + clazz.getSimpleName());
    }

}
