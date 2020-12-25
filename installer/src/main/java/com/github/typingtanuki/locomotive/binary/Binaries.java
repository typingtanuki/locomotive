package com.github.typingtanuki.locomotive.binary;

import com.github.typingtanuki.locomotive.ppa.Ppas;

/**
 * All the binaries installable through the wizard
 */
public final class Binaries {
    private Binaries() {
        super();
    }

    public static Binary wine() {
        return new AptBinary("wine", "winehq-staging", Ppas.wine())
                .aptFlags("--install-recommends")
                .title("WINE")
                .description("Compatibility layer to be able to run natively Windows binaries on Linux");
    }

    public static Binary onBoard() {
        return new AptBinary("onboard")
                .title("On-board")
                .description("On-screen keyboard");
    }

    public static Binary buildEssentials() {
        return new AptBinary("gcc", "build-essentials")
                .title("Build essentials")
                .description("Required tools to be able to build native tools");
    }

    public static Binary kodi() {
        return new AptBinary("kodi")
                .extraPackages("python3", "python3-dbus")
                .title("Kodi")
                .description("Movie player and library");
    }

    public static Binary antimicroX() {
        return new GithubBinary("antimicrox", "AntiMicroX", "antimicrox")
                .title("AntiMicroX")
                .description("Bind gamepad to keyboard/mouse");
    }

    public static Binary gamehub() {
        return new AptBinary("gamehub", Ppas.gamehub())
                .extraPackages("xcb", "icoutils")
                .title("GameHub")
                .description("Easy to use GUI gathering games from steam, GoG, itch, ...");
    }

    public static Binary steam() {
        return new AptBinary("steam")
                .extraPackages("libgtk2.0-0:i386", "libxtst6:i386")
                .title("Steam")
                .description("The Steam client for linux");
    }

    public static Binary itch() {
        return new DownloadBinary("itch", "https://itch.io/app/download?platform=linux")
                .title("Itch.io")
                .description("The Itch.io client for linux");
    }

    public static Binary retroarch() {
        return new AptBinary("retroarch")
                .extraPackages("libretro-*")
                .title("Retroarch")
                .description("Emulator manager and emulators (libretro based)");
    }
}
