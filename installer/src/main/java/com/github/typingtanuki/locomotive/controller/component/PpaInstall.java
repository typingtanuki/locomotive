package com.github.typingtanuki.locomotive.controller.component;

import com.github.typingtanuki.locomotive.ppa.Ppa;
import com.github.typingtanuki.locomotive.utils.IconUtils;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.controlsfx.glyphfont.FontAwesome;

import java.io.IOException;

public class PpaInstall extends InstallComponent {
    private final Ppa ppa;

    public PpaInstall(Ppa ppa) {
        super(
                new Label("PPA " + ppa.getTitle()),
                new Button("Install PPA!", IconUtils.getIcon(FontAwesome.Glyph.INBOX)));
        this.ppa = ppa;
    }

    @Override
    protected StepState execute() {
        try {
            ppa.install();
        } catch (IOException e) {
            return new StepState(e);
        }
        return StepState.success();
    }
}
