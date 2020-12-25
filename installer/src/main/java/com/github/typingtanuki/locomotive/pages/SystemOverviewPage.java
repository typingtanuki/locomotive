package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.ppa.Ppas;
import com.github.typingtanuki.locomotive.widgets.support.ArchitectureSupportWidget;
import com.github.typingtanuki.locomotive.widgets.support.BinarySupportWidget;
import com.github.typingtanuki.locomotive.widgets.support.PpaSupportWidget;
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

public class SystemOverviewPage extends InstallerPage {
    private final CountDownLatch latch = new CountDownLatch(5);

    private final AtomicBoolean architecture = new AtomicBoolean(false);
    private final AtomicBoolean ppaMultiverse = new AtomicBoolean(false);
    private final AtomicBoolean ppaKubuntu = new AtomicBoolean(false);
    private final AtomicBoolean ppaBackports = new AtomicBoolean(false);
    private final AtomicBoolean buildEssentials = new AtomicBoolean(false);

    public SystemOverviewPage(Deque<InstallerPage> nextPages) {
        super(nextPages);
    }

    @Override
    protected Node makeContent() {
        CoreExecutor.execute(this::waitForLatch);
        return vertical(
                new ArchitectureSupportWidget(latch, architecture),
                new PpaSupportWidget(Ppas.multiverse(), latch, ppaMultiverse),
                new PpaSupportWidget(Ppas.kubuntuUpdates(), latch, ppaKubuntu),
                new PpaSupportWidget(Ppas.kubuntuBackport(), latch, ppaBackports),
                new BinarySupportWidget(Binaries.buildEssentials(), latch, buildEssentials)
        );
    }

    private void waitForLatch() {
        try {
            latch.await();
            clearPages();
            if (!architecture.get()) {
                addPage(new Enable32BitPage(getNextPages()));
            }
            if (!ppaMultiverse.get()) {
                addPage(new AddPpaPage(Ppas.multiverse(), getNextPages()));
            }
            if (!ppaKubuntu.get()) {
                addPage(new AddPpaPage(Ppas.kubuntuUpdates(), getNextPages()));
            }
            if (!ppaBackports.get()) {
                addPage(new AddPpaPage(Ppas.kubuntuBackport(), getNextPages()));
            }
            if (!buildEssentials.get()) {
                addPage(new AddBinaryPage(Binaries.buildEssentials(), getNextPages()));
            }
            addPage(new DriverOverviewPage(getNextPages()));
            Platform.runLater(() -> getNextButton().setDisable(false));
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
        return basicFooter(true);
    }
}
