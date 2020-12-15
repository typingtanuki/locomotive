package com.github.typingtanuki.locomotive.controller.common;

import java.util.List;

public abstract class OverviewController extends InstallerPage {
    private final StepOverviewController overview;

    public OverviewController(StepOverviewController overview) {
        super();

        this.overview = overview;
    }

    protected StepOverviewController getOverview() {
        return overview;
    }

    public List<InstallerPage> listSubPages() {
        return overview.listSubPages();
    }
}
