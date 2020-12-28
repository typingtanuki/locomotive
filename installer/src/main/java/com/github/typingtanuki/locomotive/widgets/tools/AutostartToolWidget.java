package com.github.typingtanuki.locomotive.widgets.tools;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.pages.SetupToolsPage;
import com.github.typingtanuki.locomotive.utils.DialogUtils;
import javafx.application.Platform;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutostartToolWidget extends AbstractToolWidget {

    public AutostartToolWidget(String title, String description, SetupToolsPage setupToolsPage) {
        super(title, description, setupToolsPage);

        init();
        setNoTerminal();
    }

    @Override
    protected boolean isInstalled() {
        return true;
    }

    @Override
    protected void start() {
        AutostartStatus before = new AutostartStatus(Arrays.asList(
                Binaries.onBoard(),
                Binaries.gamehub(),
                Binaries.steam(),
                Binaries.antimicroX()));

        Platform.runLater(() -> {
            AutostartStatus autostartStatus = showPopup(before);
            List<Binary> toEnable = diff(autostartStatus.getEnabled(), before.getEnabled());
            List<Binary> toDisable = diff(autostartStatus.getAvailable(), before.getAvailable());

            try {
                for (Binary b : toEnable) {
                    Path desktop = AutostartStatus.desktopAutostart(b);
                    Files.createDirectories(desktop.getParent());
                    Files.write(desktop, makeDesktopFile(b));
                }
                for (Binary b : toDisable) {
                    Path desktop = AutostartStatus.desktopAutostart(b);
                    Files.deleteIfExists(desktop);
                }
                finished();
            } catch (IOException e) {
                DialogUtils.showErrorDialog(e);
            }
        });
    }

    private byte[] makeDesktopFile(Binary b) {
        String out = "[Desktop Entry]\n" +
                "Type=Application\n" +
                "Name=" + b.getTitle() + "\n" +
                "Exec=" + b.getBinary() + "\n" +
                "Comment=" + b.getDescription() + "\n";
        return out.getBytes(StandardCharsets.UTF_8);
    }

    private List<Binary> diff(List<Binary> now, List<Binary> before) {
        List<Binary> out = new ArrayList<>();
        for (Binary b : now) {
            if (!before.contains(b)) {
                out.add(b);
            }
        }
        return out;
    }

    private AutostartStatus showPopup(AutostartStatus before) {
        return new AutostartDialog(before).showAndWait().orElse(before);
    }
}
