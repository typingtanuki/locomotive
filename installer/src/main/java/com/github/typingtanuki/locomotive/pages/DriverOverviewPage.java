package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.binary.Binaries;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.i18n.I18n;
import com.github.typingtanuki.locomotive.ppa.Ppas;
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

/**
 * The page for driver related PPAs
 */
public class DriverOverviewPage extends AbstractInstallerPage {
    private final CountDownLatch latch = new CountDownLatch(2);

    private final AtomicBoolean swat = new AtomicBoolean(false);
    private final AtomicBoolean kisak = new AtomicBoolean(false);
    private final AtomicBoolean calibrator = new AtomicBoolean(false);

    public DriverOverviewPage(Deque<AbstractInstallerPage> nextPages) {
        super(nextPages);
    }

    @Override
    protected Node makeContent() {
        CoreExecutor.execute(this::waitForLatch);
        return vertical(
                new PpaSupportWidget(Ppas.xSwat(), latch, swat),
                new PpaSupportWidget(Ppas.kisak(), latch, kisak),
                new BinarySupportWidget(Binaries.xinputCalibrator(), latch, calibrator));
    }

    private void waitForLatch() {
        try {
            latch.await();
            clearPages();
            if (!swat.get()) {
                addPage(new AddPpaPage(Ppas.xSwat(), getNextPages()));
            }
            if (!kisak.get()) {
                addPage(new AddPpaPage(Ppas.kisak(), getNextPages()));
            }
            if (!calibrator.get()) {
                addPage(new AddBinaryPage(Binaries.xinputCalibrator(), getNextPages()));
            }
            if (!getNextPages().isEmpty()) {
                Platform.runLater(() -> getNextButton().setDisable(false));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected Pane makeHeader() {
        return header(
                I18n.get("driver.title"),
                I18n.get("driver.description"),
                FontAwesome.Glyph.LAPTOP);
    }

    @Override
    protected HBox makeFooter() {
        return basicFooter(true);
    }
}
