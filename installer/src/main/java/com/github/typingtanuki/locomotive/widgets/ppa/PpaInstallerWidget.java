package com.github.typingtanuki.locomotive.widgets.ppa;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.PpaInstaller;
import com.github.typingtanuki.locomotive.utils.PpaTester;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;

public class PpaInstallerWidget extends AbstractInstallWidget {
    private final Ppa ppa;

    public PpaInstallerWidget(Ppa ppa,
                              Runnable installStarts,
                              Runnable ppaIsInstalled) {
        super(I18n.get("ppaInstaller.title"),
                I18n.get("ppaInstaller.description"),
                installStarts,
                ppaIsInstalled);

        this.ppa = ppa;
    }

    @Override
    protected String actionButtonName() {
        return I18n.get("addPpa");
    }

    public void keyIsReady() {
        CoreExecutor.execute(this::updateState);
    }

    public void updateState() {
        try {
            if (PpaTester.isPpaActivated(ppa)) {
                setState(WidgetState.INSTALLED);
            } else {
                setState(WidgetState.MISSING);
                showInstallButton();
            }
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }

    @Override
    protected void doInstall() {
        try {
            PpaInstaller.installPpa(ppa, getTerminal());
            setState(WidgetState.INSTALLED);
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}
