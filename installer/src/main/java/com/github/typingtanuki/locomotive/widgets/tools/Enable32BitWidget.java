package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.comm.InstallerServer;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;

import java.io.IOException;

public class Enable32BitWidget extends AbstractInstallWidget {
    public Enable32BitWidget(Runnable installStarts, Runnable installFinished) {
        super(I18n.get("32bitInstaller.title"),
                I18n.get("32bitInstaller.description"),
                installStarts,
                installFinished);
    }

    @Override
    protected String actionButtonName() {
        return I18n.get("enable");
    }

    @Override
    protected void doInstall() {
        try {
            InstallerServer.exec(
                    getTerminal(),
                    "dpkg",
                    "--add-architecture",
                    "i386");
            installIsDone();
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}
