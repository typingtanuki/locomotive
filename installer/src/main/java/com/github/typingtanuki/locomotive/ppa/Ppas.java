package com.github.typingtanuki.locomotive.ppa;

public final class Ppas {
    private Ppas() {
        super();
    }

    public static Ppa multiverse() {
        return new Ppa("multiverse");
    }

    public static Ppa xSwat() {
        return new Ppa("ppa:ubuntu-x-swat/updates");
    }

    public static Ppa kisak() {
        return new Ppa("ppa:kisak/turtle");
    }

    public static Ppa kubuntuUpdates() {
        return new Ppa("ppa:kubuntu-ppa/ppa");
    }

    public static Ppa kubuntuBackport() {
        return new Ppa("ppa:kubuntu-ppa/backports");
    }

    public static Ppa wine() {
        return new Ppa("deb https://dl.winehq.org/wine-builds/ubuntu focal main",
                new PpaKey("winehq.org", "https://dl.winehq.org/wine-builds/winehq.key"));
    }

    public static Ppa gamehub() {
        return new Ppa("ppa:tkashkin/gamehub");
    }
}
