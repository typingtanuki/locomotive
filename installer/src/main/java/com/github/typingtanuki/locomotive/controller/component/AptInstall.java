package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

public class AptInstall extends InstallComponent {
    private final Binary binary;

    public AptInstall(Binary binary) {
        super(
                new VBox(new Label("Binary " + binary.getTitle())),
                new Button("Install Binary with APT!", IconUtils.getIcon(FontAwesome.Glyph.GIFT)));
        this.binary = binary;
    }

    @Override
    protected StepState execute() {
        ProcessMonitor processMonitor = new ProcessMonitor("Installing packages for " + binary.getTitle());
        CommonSettings.runInGui(() -> {
            getBody().getChildren().add(processMonitor.getLayout());
        });
        binary.addMonitor(processMonitor);

        try {
            binary.install();
        } catch (IOException e) {
            return new StepState(e);
        }
        return StepState.success();
    }
}