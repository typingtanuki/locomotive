package com.github.typingtanuki.locomotive.controller.common;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class AbstractOverviewController extends Pane {
    private final List<InstallerPage> subPages = new ArrayList<>();

    protected void addSubPage(InstallerPage sub) {
        subPages.add(sub);
    }

    public List<InstallerPage> listSubPages() {
        return new ArrayList<>(subPages);
    }
}
