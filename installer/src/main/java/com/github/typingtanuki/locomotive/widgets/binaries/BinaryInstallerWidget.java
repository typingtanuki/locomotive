package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryInstallerWidget extends AbstractInstallWidget implements FileTargetWidget {
    private Path fileTarget;

    public BinaryInstallerWidget(
            Binary binary,
            Runnable installStarts,
            Runnable installFinished) {
        super(binary.getTitle(),
                binary.getDescription(),
                installStarts,
                installFinished);
    }

    public void downloadFinished() {
        CoreExecutor.execute(this::updateState);
    }

    public void updateState() {
        setState(WidgetState.MISSING);
        showInstallButton();
    }

    @Override
    protected void doInstall() {
        try {
            ProcessExec.exec(getTerminal(), fileTarget.toString());
            Files.deleteIfExists(fileTarget);
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }

    @Override
    public void setFileTarget(Path fileTarget) {
        this.fileTarget = fileTarget.toAbsolutePath();
    }
}
