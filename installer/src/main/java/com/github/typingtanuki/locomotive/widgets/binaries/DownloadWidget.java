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
    private final Binary binary;
    private final String extension;
    private final FileTargetWidget fileTargetWidget;
    private final Path target;

    private String url;

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

        target = Paths.get("cache").resolve(binary.getBinary() + "-installer." + extension);

        setState(WidgetState.MISSING);
    }

    @Override
    protected String actionButtonName() {
        return I18n.get("download");
    }

    @Override
    protected void doInstall() {
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
    public void setUrlTarget(String url) {
        this.url = url;
    }

    public void urlResolved() {
        if (isDownloaded()) {
            setState(WidgetState.INSTALLED);
        } else {
            showInstallButton();
        }
    }

    public boolean isDownloaded() {
        return Files.exists(target);
    }
}
