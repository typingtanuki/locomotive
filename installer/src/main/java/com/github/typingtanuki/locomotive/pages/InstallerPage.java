package com.github.typingtanuki.locomotive.pages;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static com.github.typingtanuki.locomotive.utils.LayoutUtils.setSize;

public abstract class InstallerPage extends BorderPane {
    public InstallerPage() {
        setSize(this);
    }

    public void attached() {
        setTop(makeHeader());
        setCenter(makeContent());
        setBottom(makeFooter());
    }

    protected abstract Node makeContent();

    protected abstract Pane makeHeader();

    protected abstract Pane makeFooter();
}
