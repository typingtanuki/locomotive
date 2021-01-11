package com.github.typingtanuki.locomotive;

import com.github.typingtanuki.locomotive.comm.InstallerMessenger;
import com.github.typingtanuki.locomotive.comm.MessageType;

import java.io.IOException;

public final class InstallerAdmin {
    private static InstallerMessenger messenger;

    private InstallerAdmin() {
        super();
    }

    public static void start(int port) throws IOException {
        messenger = new InstallerMessenger(port);
        messenger.send(-1, true, true, MessageType.shake, "");
    }
}
