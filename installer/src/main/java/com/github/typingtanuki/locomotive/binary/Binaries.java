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
                .aptFlags("--install-recommends");
    }

    public static Binary onBoard() {
        return new AptBinary("onboard");
    }

    public static Binary buildEssentials() {
        return new AptBinary("gcc", "build-essentials");
    }

    public static Binary kodi() {
        return new AptBinary("kodi").extraPackages("python3", "python3-dbus");
    }

    public static Binary antimicrox() {
        return new GithubBinary("antimicrox", "AntiMicroX", "antimicrox");
    }

    public static Binary legendary() {
        return new GithubBinary("legendary", "derrod", "legendary");
    }

    public static Binary gamehub() {
        return new AptBinary("gamehub",
                Ppas.gamehub()).extraPackages("xcb", "icoutils");
    }

    public static Binary steam() {
        return new AptBinary("steam").extraPackages("libgtk2.0-0:i386", "libxtst6:i386");
    }

    public static Binary itch() {
        return new DownloadBinary("itch",
                "https://itch.io/app/download?platform=linux",
                "unknown");
    }

    public static Binary retroarch() {
        return new AptBinary("retroarch").extraPackages("libretro-*");
    }

    public static Binary xinputCalibrator() {
        return new AptBinary("xinput_calibrator", "xinput-calibrator");
    }
}
