package com.github.typingtanuki.locomotive.controller.welcome;

import com.github.typingtanuki.locomotive.controller.common.InstallerPage;

public class WelcomePageController extends InstallerPage {

    public WelcomePageController() {
        super();
        setCenter(new WelcomeController());
        setBottom(getFooter());
    }
}