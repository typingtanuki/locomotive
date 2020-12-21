package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

public class DownloadInstall extends InstallComponent {
    private final Binary binary;

    public DownloadInstall(Binary binary) {
        super(
                new Label("Binary " + binary.getTitle()),
                new Button("Download and install Binary!", IconUtils.getIcon(FontAwesome.Glyph.DOWNLOAD)));
        this.binary = binary;
    }

    @Override
    protected StepState execute() {
        try {
            binary.install();
        } catch (IOException e) {
            return new StepState(e);
        }
        return StepState.success();
    }
}
