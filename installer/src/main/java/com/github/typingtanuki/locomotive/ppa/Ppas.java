package com.github.typingtanuki.locomotive.ppa;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class Ppas {
    private static final List<Ppa> PPAS = new LinkedList<>();
    static{
        PPAS.add(new Ppa("multiverse"));
        PPAS.add(new Ppa("ppa:kubuntu-ppa/ppa"));
        PPAS.add(new Ppa("ppa:kubuntu-ppa/backports"));
        PPAS.add(new Ppa("deb https://dl.winehq.org/wine-builds/ubuntu focal main",
                new PpaKey(
                        "winehq.org",
                        "https://dl.winehq.org/wine-builds/winehq.key")));
        PPAS.add(new Ppa("ppa:ubuntu-x-swat/updates"));
        PPAS.add(new Ppa("ppa:oibaf/graphics-drivers"));
        PPAS.add(new Ppa("ppa:tkashkin/gamehub"));
    }

    private Ppas() {
        super();
    }

    public static void checkPpas() throws IOException {
        for(Ppa ppa:PPAS){
            ppa.checkInstalled();
        }
    }

    public static void printState(){
        for(Ppa ppa:PPAS){
            System.out.println(ppa);
        }
    }
}
