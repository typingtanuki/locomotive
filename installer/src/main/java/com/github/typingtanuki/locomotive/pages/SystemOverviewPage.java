package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.navigation.NavigationCore;
import com.github.typingtanuki.locomotive.widgets.support.ArchitectureSupportWidget;
import com.github.typingtanuki.locomotive.widgets.support.PpaSupportWidget;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.github.typingtanuki.locomotive.utils.ButtonUtils.button;
import static com.github.typingtanuki.locomotive.utils.ButtonUtils.exitButton;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.*;

public class SystemOverviewPage extends InstallerPage {
    private final CountDownLatch latch = new CountDownLatch(4);
    private final Deque<InstallerPage> nextPages = new ArrayDeque<>();

    private Button nextButton;
    private final AtomicBoolean architecture = new AtomicBoolean(false);
    private final AtomicBoolean ppaMultiverse = new AtomicBoolean(false);
    private final AtomicBoolean ppaKubuntu = new AtomicBoolean(false);
    private final AtomicBoolean ppaBackports = new AtomicBoolean(false);

    @Override
    protected Node makeContent() {
        CoreExecutor.execute(this::waitForLatch);
        return vertical(
                new ArchitectureSupportWidget(latch, architecture),
                new PpaSupportWidget("multiverse", latch, ppaMultiverse),
                new PpaSupportWidget("ppa:kubuntu-ppa/ppa", latch, ppaKubuntu),
                new PpaSupportWidget("ppa:kubuntu-ppa/backports", latch, ppaBackports)
        );
    }

    private void waitForLatch() {
        try {
            latch.await();
            System.out.println("Latches are done");
            nextPages.clear();
            if (architecture.get()) {
                nextPages.add(new Enable32BitPage(nextPages));
            }
            if (ppaMultiverse.get()) {
                nextPages.add(new AddPpaPage("multiverse", nextPages));
            }
            if (ppaKubuntu.get()) {
                nextPages.add(new AddPpaPage("ppa:kubuntu-ppa/ppa", nextPages));
            }
            if (ppaBackports.get()) {
                nextPages.add(new AddPpaPage("ppa:kubuntu-ppa/backports", nextPages));
            }
            nextPages.add(new RecommendedPackagePage(nextPages));
            Platform.runLater(() -> nextButton.setDisable(false));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("overview.title"),
                I18n.get("overview.description"),
                FontAwesome.Glyph.GEARS);
    }

    @Override
    protected HBox makeFooter() {
        nextButton = disabled(button("next", FontAwesome.Glyph.ARROW_RIGHT, this::doNext));
        return horizontal(
                nextButton,
                exitButton());
    }

    private void doNext() {
        InstallerPage next = nextPages.pollFirst();
        NavigationCore.changePage(next);
    }
}
