package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

public class GithubInstall extends InstallComponent {
    private final Binary binary;

    public GithubInstall(Binary binary) {
        super(
                new VBox(new Label("Binary " + binary.getTitle())),
                new Button("Install Binary from github!", IconUtils.getIcon(FontAwesome.Glyph.GITHUB)));

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