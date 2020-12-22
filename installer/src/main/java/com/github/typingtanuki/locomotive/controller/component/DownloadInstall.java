package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.controller.monitor.DownloadMonitor;
import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

public class DownloadInstall extends InstallComponent {
    private final Binary binary;

    public DownloadInstall(Binary binary) {
        super(new VBox(),
                new Button("Download and install Binary!", IconUtils.getIcon(FontAwesome.Glyph.DOWNLOAD)));
        this.binary = binary;
        vbox(binary, getBody());
    }

    private static void vbox(Binary binary, VBox center) {
        center.getChildren().add(new Label("Binary " + binary.getTitle()));
    }

    @Override
    protected StepState execute() {
        try {
            DownloadMonitor downloadMonitor = new DownloadMonitor("Downloading installer for " + binary.getTitle());
            CommonSettings.runInGui(() -> {
                getBody().getChildren().add(downloadMonitor.getLayout());
            });
            ProcessMonitor processMonitor = new ProcessMonitor("Running installer for " + binary.getTitle());
            CommonSettings.runInGui(() -> {
                getBody().getChildren().add(processMonitor.getLayout());
            });

            binary.addMonitor(downloadMonitor);
            binary.addMonitor(processMonitor);
            binary.install();
        } catch (IOException e) {
            return new StepState(e);
        }
        return StepState.success();
    }
}