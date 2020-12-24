package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.AptBinary;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.utils.*;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;

public class AptInstallerWidget extends AbstractInstallWidget {
    private final AptBinary aptBinary;

    public AptInstallerWidget(AptBinary aptBinary, Runnable installStarts, Runnable installFinished) {
        super(aptBinary.getTitle(), aptBinary.getDescription(), installStarts, installFinished);
        this.aptBinary = aptBinary;
    }

    public void ppaIsReady() {
        CoreExecutor.execute(this::updateState);
    }

    public void updateState() {
        if (PackageTester.isBinaryOnPath(aptBinary)) {
            setState(WidgetState.INSTALLED);
        } else {
            setState(WidgetState.MISSING);
            showInstallButton();
        }
    }

    @Override
    protected void doInstall() {
        try {
            PackageInstaller.installBinary(aptBinary, getTerminal());
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}
