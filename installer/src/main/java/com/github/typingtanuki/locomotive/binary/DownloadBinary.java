package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
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
        DownloadMonitor downloadMonitor = monitor(DownloadMonitor.class);

        Path installer = Paths.get("./cache/").resolve(binary + "-installer");
        System.out.println("Downloading to " + installer);
        Download.inFile(url, installer, downloadMonitor);
        System.out.println("Downloading to " + installer + ": OK");
        System.out.println("Changing permissions of " + installer);
        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(installer);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(installer, permissions);
        System.out.println("Changing permissions of " + installer + ": OK");


        ProcessMonitor processMonitor = monitor(ProcessMonitor.class);
        System.out.println("Starting process");
        try {
            ProcessExec processExec = new ProcessExec(installer.toAbsolutePath().toString(), processMonitor);
            processExec.exec();
            return processExec.getExit() == 0 && isInstalled();
        } finally {
            System.out.println("Process finished");
        }
    }

    @Override
    public InstallType getType() {
        return InstallType.DOWNLOAD;
    }
}
