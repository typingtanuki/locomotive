package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.DownloadUtils;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadWidget extends AbstractInstallWidget implements UrlTargetWidget {
    private final FileTargetWidget fileTargetWidget;
    private final Binary binary;
    private final String extension;

    private String url;
    private String version;

    public DownloadWidget(Binary binary,
                          String extension,
                          FileTargetWidget fileTargetWidget,
                          Runnable installStarts,
                          Runnable installFinished) {
        super(I18n.get("downloadInstaller.title"),
                I18n.get("downloadInstaller.description"),
                installStarts,
                installFinished);

        this.binary = binary;
        this.extension = extension;

        this.fileTargetWidget = fileTargetWidget;

        setState(WidgetState.MISSING);
    }

    @Override
    protected String actionButtonName() {
        return I18n.get("download");
    }

    private Path fileName() {
        String baseName = binary.getBinary() + "-installer-" + version + "." + extension;
        return Paths.get("cache").resolve(baseName);
    }

    @Override
    protected void doInstall() {
        Path target = fileName();
        try {
            DownloadUtils.inFile(url, target, getDownload());
            DownloadUtils.makeExecutable(target);
            fileTargetWidget.setFileTarget(target);
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }

    @Override
    public void setTarget(String url, String version) {
        this.url = url;
        this.version = version;
    }

    public void urlResolved() {
        if (isDownloaded()) {
            setState(WidgetState.INSTALLED);
        } else {
            showInstallButton();
        }
    }

    public boolean isDownloaded() {
        return Files.exists(fileName());
    }
}
