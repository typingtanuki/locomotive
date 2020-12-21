package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.controller.monitor.Monitor;
import com.github.typingtanuki.locomotive.utils.PackageTester;
import com.sun.glass.ui.Clipboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Binary {
    private final String binary;
    private String title;
    private String description;
    private final List<Monitor> monitors = new ArrayList<>();

    public Binary(String binary) {
        super();

        this.binary = binary;
        this.title = binary;
    }

    public Binary title(String title) {
        this.title = title;
        return this;
    }

    public Binary description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Binary{" +
                ", binary='" + binary + '\'' +
                '}';
    }

    public boolean isInstalled() {
        return PackageTester.isBinaryOnPath(binary);
    }

    public abstract boolean install() throws IOException;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public abstract InstallType getType();

    public void addMonitor(Monitor monitor) {
        monitors.add(monitor);
    }

    public <T extends Monitor> T monitor(Class<? extends T> clazz) {
        for (Monitor monitor : monitors) {
            if (clazz.isAssignableFrom(monitor.getClass())) {
                return (T) monitor;
            }
        }
        throw new IllegalStateException("Could not find monitor of class " + clazz.getSimpleName());
    }
}
