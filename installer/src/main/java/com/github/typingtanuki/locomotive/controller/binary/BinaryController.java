package com.github.typingtanuki.locomotive.controller.binary;

import com.github.typingtanuki.locomotive.binary.Binary;
import com.github.typingtanuki.locomotive.controller.common.Header;
import com.github.typingtanuki.locomotive.controller.common.InstallerPage;

public class BinaryController extends InstallerPage {
    public BinaryController(Binary binary) {
        super();
        setTop(new Header(binary.getTitle(), binary.getDescription()));
        setCenter(new BinaryInstallController(binary));
        setBottom(getFooter());
    }
}