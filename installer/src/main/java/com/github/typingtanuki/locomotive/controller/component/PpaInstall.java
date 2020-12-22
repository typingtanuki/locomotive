package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.controller.monitor.ProcessMonitor;
import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.settings.CommonSettings;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

public class PpaInstall extends InstallComponent {
    private final Ppa ppa;

    public PpaInstall(Ppa ppa) {
        super(
                new VBox(new Label("PPA " + ppa.getTitle())),
                new Button("Install PPA!", IconUtils.getIcon(FontAwesome.Glyph.INBOX)));
        this.ppa = ppa;
    }

    @Override
    protected StepState execute() {
        ProcessMonitor processMonitor = new ProcessMonitor("Setting up PPA " + ppa.getTitle());
        CommonSettings.runInGui(() -> {
            getBody().getChildren().add(processMonitor.getLayout());
        });
        ppa.addMonitor(processMonitor);

        try {
            ppa.install();
        } catch (IOException e) {
            return new StepState(e);
        }
        return StepState.success();
    }
}
