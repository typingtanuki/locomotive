package com.github.typingtanuki.locomotive.controller;

import javafx.stage.Stage;

public class PpaOverviewPpaController extends InstallerPage {
    public PpaOverviewPpaController(Stage stage) {
        super();
        setCenter(new PpaOverviewController());
        setBottom(new FooterController(new Navigator(stage, null)).getView());
    }
}
