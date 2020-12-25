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

public class RecommendedPackagePage extends InstallerPage {
    private final CountDownLatch latch = new CountDownLatch(3);

    private final AtomicBoolean wine = new AtomicBoolean(false);
    private final AtomicBoolean onBoard = new AtomicBoolean(false);
    private final AtomicBoolean antiMicroX = new AtomicBoolean(false);
    private final AtomicBoolean kodi = new AtomicBoolean(false);

    public RecommendedPackagePage(Deque<InstallerPage> nextPages) {
        super(nextPages);
    }


    @Override
    protected Node makeContent() {
        CoreExecutor.execute(this::waitForLatch);
        return vertical(
                new BinarySupportWidget(Binaries.wine(), latch, wine),
                new BinarySupportWidget(Binaries.onBoard(), latch, onBoard),
                new BinarySupportWidget(Binaries.antimicroX(), latch, antiMicroX),
                new BinarySupportWidget(Binaries.kodi(), latch, kodi));
    }

    private void waitForLatch() {
        try {
            latch.await();
            clearPages();
            if (!wine.get()) {
                addPage(new AddBinaryPage(Binaries.wine(), getNextPages()));
            }
            if (!onBoard.get()) {
                addPage(new AddBinaryPage(Binaries.onBoard(), getNextPages()));
            }
            if (!antiMicroX.get()) {
                addPage(new AddBinaryPage(Binaries.antimicroX(), getNextPages()));
            }
            if (!kodi.get()) {
                addPage(new AddBinaryPage(Binaries.kodi(), getNextPages()));
            }
            addPage(new GamePackagePage(getNextPages()));
            Platform.runLater(() -> getNextButton().setDisable(false));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("recommendedPackages.title"),
                I18n.get("recommendedPackages.description"),
                FontAwesome.Glyph.ARCHIVE);
    }

    @Override
    protected HBox makeFooter() {
        return basicFooter(true);
    }
}
