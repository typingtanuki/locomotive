package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.components.GlitchLabel;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayDeque;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.horizontal;
import static com.github.typingtanuki.locomotive.utils.StyleUtils.*;

/**
 * The title page
 */
public class WelcomePage extends AbstractInstallerPage {
    public WelcomePage() {
        super(new ArrayDeque<>());
    }

    @Override
    protected Node makeContent() {
        GlitchLabel label = new GlitchLabel(I18n.get("welcome"), true);
        Bloom effect = new Bloom(0.001);
        effect.setInput(label.getEffect());
        label.setEffect(effect);
        return stack(
                panel(withClass(label, CLASS_WELCOME)),
                withClass(panel(null), CLASS_OVERLAY));
    }

    private StackPane stack(Pane... content) {
        StackPane out = new StackPane();
        out.getChildren().addAll(content);
        return out;
    }

    private BorderPane panel(Node content) {
        BorderPane out = new BorderPane();
        if (content != null) {
            out.setCenter(content);
        }
        return out;
    }

    @Override
    protected Pane makeHeader() {
        return new VBox();
    }

    @Override
    protected Pane makeFooter() {
        return horizontal(
                button(I18n.get("start"), FontAwesome.Glyph.CHECK, this::doStart),
                exitButton());
    }

    public void doStart() {
        NavigationCore.changePage(new MenuPage());
    }
}
