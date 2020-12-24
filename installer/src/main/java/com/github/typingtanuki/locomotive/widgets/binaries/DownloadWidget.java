package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.DownloadUtils;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadWidget extends AbstractInstallWidget implements UrlTargetWidget {
    private final Binary binary;
    private final FileTargetWidget fileTargetWidget;

    private String url;

    public DownloadWidget(Binary binary,
                          FileTargetWidget fileTargetWidget,
                          Runnable installStarts,
                          Runnable installFinished) {
        super(binary.getTitle(),
                binary.getDescription(),
                installStarts,
                installFinished);

        this.binary = binary;
        this.fileTargetWidget = fileTargetWidget;
    }

    @Override
    protected void doInstall() {
        try {
            Path target = Paths.get("cache").resolve(binary.getBinary() + "-installer");
            DownloadUtils.inFile(url, target);
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
        showInstallButton();
    }
}
