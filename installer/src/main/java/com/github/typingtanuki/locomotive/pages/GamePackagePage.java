package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.widgets.support.BinarySupportWidget;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.Deque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;

/**
 * The page for installing gaming related packages
 */
public class GamePackagePage extends AbstractInstallerPage {
    private final CountDownLatch latch = new CountDownLatch(4);

    private final AtomicBoolean steam = new AtomicBoolean(false);
    private final AtomicBoolean gamehub = new AtomicBoolean(false);
    private final AtomicBoolean itch = new AtomicBoolean(false);
    private final AtomicBoolean retroarch = new AtomicBoolean(false);

    public GamePackagePage(Deque<AbstractInstallerPage> nextPages) {
        super(nextPages);
    }


    @Override
    protected Node makeContent() {
        CoreExecutor.execute(this::waitForLatch);
        return vertical(
                new BinarySupportWidget(Binaries.steam(), latch, steam),
                new BinarySupportWidget(Binaries.gamehub(), latch, gamehub),
                new BinarySupportWidget(Binaries.itch(), latch, itch),
                new BinarySupportWidget(Binaries.retroarch(), latch, retroarch)
        );
    }

    private void waitForLatch() {
        try {
            latch.await();
            clearPages();
            if (!steam.get()) {
                addPage(new AddBinaryPage(Binaries.steam(), getNextPages()));
            }
            if (!gamehub.get()) {
                addPage(new AddBinaryPage(Binaries.gamehub(), getNextPages()));
            }
            if (!itch.get()) {
                addPage(new AddBinaryPage(Binaries.itch(), getNextPages()));
            }
            if (!retroarch.get()) {
                addPage(new AddBinaryPage(Binaries.retroarch(), getNextPages()));
            }
            addPage(new SetupToolsPage(getNextPages()));
            Platform.runLater(() -> getNextButton().setDisable(false));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("gamePackages.title"),
                I18n.get("gamePackages.description"),
                FontAwesome.Glyph.GAMEPAD);
    }

    @Override
    protected HBox makeFooter() {
        return basicFooter(true);
    }
}
