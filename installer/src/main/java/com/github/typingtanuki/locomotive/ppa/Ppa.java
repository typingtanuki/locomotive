package com.github.typingtanuki.locomotive.ppa;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.controller.monitor.Monitor;
import com.github.typingtanuki.locomotive.utils.PackageTester;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.sun.glass.ui.Clipboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ppa {
    private final String ppa;
    private final PpaKey key;
    private final List<Monitor> monitors = new ArrayList<>();

    private Boolean installed = null;
    private String title;
    private String description;

    public Ppa(String ppa, PpaKey key) {
        super();

        this.ppa = ppa;
        this.key = key;
    }

    public Ppa(String ppa) {
        this(ppa, null);
    }

    public Ppa title(String title) {
        this.title = title;
        return this;
    }

    public Ppa description(String description) {
        this.description = description;
        return this;
    }

    public boolean isInstalled() throws IOException {
        return PackageTester.isPpaActivated(ppa);
    }

    public void install() throws IOException {
        if (installed != null && installed) {
            return;
        }


        if (key != null) {
            if (!key.isInstalled()) {
                key.install(monitor(DownloadMonitor.class));
            }
        }

        ProcessExec exec = new ProcessExec("apt-add-repository");
        exec.exec(ppa, "-y");
        exec.checkSuccess();
    }

    public void checkInstalled() throws IOException {
        installed = isInstalled();
    }

    @Override
    public String toString() {
        return "Ppa{" +
                "installed=" + installed +
                ", title='" + title + '\'' +
                '}';
    }

    public String getName() {
        return ppa;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

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
