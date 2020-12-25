package com.github.typingtanuki.locomotive.ppa;

public final class Ppas {
    private Ppas() {
        super();
    }

    public static Ppa multiverse() {
        return new Ppa("multiverse",
                "Multiverse",
                "Official kubuntu repository with extra tools");
    }

    public static Ppa xSwat() {
        return new Ppa("ppa:ubuntu-x-swat/updates",
                "X-SWAT",
                "Updates to the X graphical server, for improved performance");
    }

    public static Ppa oibaf() {
        return new Ppa("ppa:oibaf/graphics-drivers",
                "Oibaf Graphics drivers",
                "Updates to the intel graphics driver, for improved performance");
    }

    public static Ppa kubuntuUpdates() {
        return new Ppa("ppa:kubuntu-ppa/ppa",
                "Kubuntu Updates",
                "Official Kubuntu repository with KDE point updates");
    }

    public static Ppa kubuntuBackport() {
        return new Ppa("ppa:kubuntu-ppa/backports",
                "Kubuntu Backports",
                "Official Kubuntu repository, bringing extra updates to the LTS");
    }

    public static Ppa wine() {
        return new Ppa("deb https://dl.winehq.org/wine-builds/ubuntu focal main",
                "Wine",
                "Compatibility layer to be able to use Windows software on linux",
                new PpaKey("winehq.org", "https://dl.winehq.org/wine-builds/winehq.key"));
    }

    public static Ppa gamehub() {
        return new Ppa("ppa:tkashkin/gamehub",
                "Gamehub",
                "Easy to use GUI gathering games from steam, GoG, itch, ...");
    }
}
