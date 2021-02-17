package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.utils.ProcessExec;
import com.github.typingtanuki.locomotive.utils.ProcessFailedException;
import com.github.typingtanuki.locomotive.utils.ProcessNotAuthorized;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

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
            ProcessExec.sudoExec(
                    getTerminal(),
                    "dpkg",
                    "--add-architecture",
                    "i386");
            installIsDone();
        }catch (ProcessNotAuthorized | ProcessFailedException e){
            setState(WidgetState.FAILED);
        }
    }
}
