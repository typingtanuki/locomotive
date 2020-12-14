package com.github.typingtanuki.locomotive.controller;

import javafx.stage.Stage;

public class WelcomePageController extends InstallerPage {

    public WelcomePageController(Stage stage) {
        super();
        setCenter(new WelcomeController());
        setBottom(new FooterController(new Navigator(stage, new PpaOverviewPpaController(stage))).getView());
    }
}
