package com.github.typingtanuki.locomotive.widgets.binaries;

import com.github.typingtanuki.locomotive.binary.AptBinary;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.PackageInstaller;
import com.github.typingtanuki.locomotive.utils.PackageTester;
import com.github.typingtanuki.locomotive.utils.ProcessFailedException;
import com.github.typingtanuki.locomotive.utils.ProcessNotAuthorized;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;

public class AptInstallerWidget extends AbstractInstallWidget {
    private final AptBinary aptBinary;

    public AptInstallerWidget(AptBinary aptBinary, Runnable installStarts, Runnable installFinished) {
        super(I18n.get("aptInstaller.title"),
                I18n.get("aptInstaller.description"),
                installStarts,
                installFinished);
        this.aptBinary = aptBinary;
        setState(WidgetState.MISSING);
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
            PackageInstaller.installBinary(aptBinary, getTerminal(), getDownload());
            setState(WidgetState.INSTALLED);
        } catch (ProcessNotAuthorized | ProcessFailedException | IOException e) {
            setState(WidgetState.FAILED);
        }
    }
}
