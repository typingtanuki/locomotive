package com.github.typingtanuki.locomotive.widgets.ppa;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.ppa.PpaKey;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import com.github.typingtanuki.locomotive.utils.PpaInstaller;
import com.github.typingtanuki.locomotive.utils.PpaTester;
import com.github.typingtanuki.locomotive.widgets.AbstractInstallWidget;
import com.github.typingtanuki.locomotive.widgets.support.WidgetState;

import java.io.IOException;

public class PpaKeyInstallerWidget extends AbstractInstallWidget {
    private final PpaKey key;

    public PpaKeyInstallerWidget(PpaKey key,
                                 Runnable installStarts,
                                 Runnable keyIsInstalled) {
        super(keyTitle(key), keyDescription(key), installStarts, keyIsInstalled);

        this.key = key;

        if (key == null) {
            setState(WidgetState.INSTALLED);
        } else {
            CoreExecutor.execute(this::checkKey);
        }
    }

    private static String keyTitle(PpaKey key) {
        if (key == null) {
            return I18n.get("ppaKeyInstaller.none.title");
        }
        return I18n.get("ppaKeyInstaller.title", key.getKeyName());
    }

    private static String keyDescription(PpaKey key) {
        if (key == null) {
            return I18n.get("ppaKeyInstaller.none.description");
        }
        return I18n.get("ppaKeyInstaller.description");
    }

    private void checkKey() {
        try {
            if (PpaTester.isPpaKeyActivated(key)) {
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
            PpaInstaller.installKey(key, getTerminal());
            installIsDone();
        } catch (IOException e) {
            DialogUtils.showErrorDialog(e);
        }
    }
}
