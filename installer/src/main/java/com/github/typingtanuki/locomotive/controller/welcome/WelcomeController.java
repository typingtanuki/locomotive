package com.github.typingtanuki.locomotive.controller.welcome;

import com.github.typingtanuki.locomotive.controller.common.AbstractCenter;

public class WelcomeController extends AbstractCenter {
    public WelcomeController() {
        super();
        setContent(new WelcomeView());
    }
}
