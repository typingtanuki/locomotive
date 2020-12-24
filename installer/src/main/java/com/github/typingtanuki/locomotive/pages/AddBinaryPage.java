package com.github.typingtanuki.locomotive.pages;

import com.github.typingtanuki.locomotive.binary.AptBinary;
import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.binary.DownloadBinary;
import com.github.typingtanuki.locomotive.binary.GithubBinary;
import com.github.typingtanuki.locomotive.executor.CoreExecutor;
import com.github.typingtanuki.locomotive.widgets.AbstractWidget;
import com.github.typingtanuki.locomotive.widgets.binaries.*;
import com.github.typingtanuki.locomotive.widgets.ppa.PpaInstallerWidget;
import com.github.typingtanuki.locomotive.widgets.ppa.PpaKeyInstallerWidget;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.controlsfx.glyphfont.FontAwesome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.header;
import static com.github.typingtanuki.locomotive.utils.LayoutUtils.vertical;

public class AddBinaryPage extends InstallerPage {
    private final Binary binary;

    public AddBinaryPage(Binary binary, Deque<InstallerPage> nextPages) {
        super(nextPages);

        this.binary = binary;
    }

    @Override
    protected Node makeContent() {
        if (binary instanceof AptBinary) {
            return aptBinaryContent((AptBinary) binary);
        }
        if (binary instanceof DownloadBinary) {
            return downloadBinaryContent((DownloadBinary) binary);
        }
        if (binary instanceof GithubBinary) {
            return githubContent((GithubBinary) binary);
        }
        return new Label("Unknown binary type " + binary.getClass().getSimpleName());
    }

    private Node githubContent(GithubBinary binary) {
        DebInstallerWidget debInstallerWidget = new DebInstallerWidget(
                binary,
                this::installStarts,
                this::installFinished);
        DownloadWidget downloadWidget = new DownloadWidget(
                binary,
                debInstallerWidget,
                this::installStarts,
                debInstallerWidget::downloadFinished);
        GithubWidget resolveRelease = new GithubWidget(
                binary,
                downloadWidget,
                this::installStarts,
                downloadWidget::urlResolved);

        return vertical(
                resolveRelease,
                downloadWidget,
                debInstallerWidget);
    }

    private Node downloadBinaryContent(DownloadBinary binary) {
        BinaryInstallerWidget binaryInstallerWidget = new BinaryInstallerWidget(
                binary,
                this::installStarts,
                this::installFinished);
        DownloadWidget downloadWidget = new DownloadWidget(
                binary,
                binaryInstallerWidget,
                this::installStarts,
                binaryInstallerWidget::downloadFinished);
        downloadWidget.setUrlTarget(binary.getUrl());
        downloadWidget.urlResolved();

        return vertical(
                downloadWidget,
                binaryInstallerWidget);
    }

    private Node aptBinaryContent(AptBinary binary) {
        ArrayList<AbstractWidget> widgets = new ArrayList<>();

        AptInstallerWidget aptInstallerWidget = new AptInstallerWidget(
                binary,
                this::installStarts,
                this::installFinished);
        widgets.add(aptInstallerWidget);
        if (binary.getPpa() != null) {
            PpaInstallerWidget ppaInstallerWidget = new PpaInstallerWidget(
                    binary.getPpa(),
                    this::installStarts,
                    aptInstallerWidget::ppaIsReady);
            widgets.add(ppaInstallerWidget);
            PpaKeyInstallerWidget ppaKeyInstallerWidget = new PpaKeyInstallerWidget(
                    binary.getPpa().getKey(),
                    this::installStarts,
                    ppaInstallerWidget::keyIsReady);
            widgets.add(ppaKeyInstallerWidget);
        } else {
            CoreExecutor.execute(aptInstallerWidget::ppaIsReady);
        }
        Collections.reverse(widgets);
        return vertical(widgets.toArray(new AbstractWidget[0]));
    }

    @Override
    protected Pane makeHeader() {
        return header(
                binary.getTitle(),
                binary.getDescription(),
                FontAwesome.Glyph.FILE_CODE_ALT);
    }

    @Override
    protected Pane makeFooter() {
        return basicFooter(false);
    }

    public void installStarts() {
        Platform.runLater(() -> getNextButton().setDisable(true));
    }

    public void installFinished() {
        Platform.runLater(() -> getNextButton().setDisable(false));
    }
}
