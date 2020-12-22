package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
import com.github.typingtanuki.locomotive.utils.Download;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(DownloadBinary.class);

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
        LOGGER.info("Downloading to " + installer);
        Download.inFile(url, installer, downloadMonitor);
        LOGGER.info("Downloading to " + installer + ": OK");
        LOGGER.info("Changing permissions of " + installer);
        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(installer);
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);
        Files.setPosixFilePermissions(installer, permissions);
        LOGGER.info("Changing permissions of " + installer + ": OK");


        ProcessMonitor processMonitor = monitor(ProcessMonitor.class);
        ProcessExec processExec = new ProcessExec(installer.toAbsolutePath().toString(), processMonitor);
        processExec.exec();
        return processExec.getExit() == 0 && isInstalled();
    }

    @Override
    public InstallType getType() {
        return InstallType.DOWNLOAD;
    }
}
