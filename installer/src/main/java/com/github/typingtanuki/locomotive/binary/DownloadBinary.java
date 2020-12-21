package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.utils.Download;
import com.github.typingtanuki.locomotive.utils.ProcessExec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/**
 * A binary where the installer comes from a download link
 */
public class DownloadBinary extends Binary {
    /**
     * The URL pointing to the installer
     */
    private final String url;
    private final String binary;

    public DownloadBinary(String binary, String url) {
        super(binary);
        this.binary = binary;
        this.url = url;
    }

    @Override
    public boolean install() throws IOException {
        DownloadMonitor monitor = monitor(DownloadMonitor.class);

        Path installer = Paths.get("./cache/").resolve(binary + "-installer");
        Download.inFile(url, installer, monitor);
        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(installer);
        permissions.add(PosixFilePermission.OTHERS_READ);
        permissions.add(PosixFilePermission.OTHERS_WRITE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(installer, permissions);

        ProcessExec processExec = new ProcessExec(installer.toAbsolutePath().toString());
        return processExec.getExit() == 0 && isInstalled();
    }

    @Override
    public InstallType getType() {
        return InstallType.DOWNLOAD;
    }
}
