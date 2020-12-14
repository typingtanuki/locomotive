package com.github.typingtanuki.locomotive.binary;

import java.util.ArrayList;
import java.util.List;

public final class Binaries {
    private static final List<Binary> BINARIES = new ArrayList<>();

    static {
        BINARIES.add(new Binary("dconf", "donf-cli", true)
                .title("Dconf")
                .description("Configuration tool for gnome application"));
        BINARIES.add(new Binary("curl", true)
                .title("cURL")
                .description("Command-line tool for access REST APIs and downloading files"));
        BINARIES.add(new Binary("wget", true)
                .title("wget")
                .description("Command^line tool for downloading files"));
        BINARIES.add(new Binary("gcc", "build-essentials", true)
                .title("Build essentials")
                .description("Required tools to be able to build native tools"));
        BINARIES.add(new Binary("dos2unix", true)
                .title("DOS 2 Unix")
                .description("Tool to convert files made for windows into file made for Linux"));
        BINARIES.add(new Binary("wine", "winehq-staging", true)
                .title("WINE")
                .description("Compatibility layer to be able to run natively Windows binaries on Linux")
                .aptFlags("--install-recommends"));
        BINARIES.add(new Binary("gamehub", false)
                .title("GameHub")
                .description("Easy to use GUI gathering games from steam, GoG, itch, ...")
                .extraPackages("xcb", "icoutils"));
        BINARIES.add(new Binary("steam", false)
                .title("Steam")
                .description("The Steam client for linux")
                .extraPackages("libgtk2.0-0:i386", "libxtst6:i386"));
        BINARIES.add(new Binary("itch", false)
                .title("Itch.io")
                .description("The Itch.io client for linux")
                .fromDownload("https://itch.io/app/download?platform=linux"));
        BINARIES.add(new Binary("retroarch", false)
                .title("Retroarch")
                .description("Emulator manager and emulators (libretro based)")
                .extraPackages("libretro-*")
                .post((Binary binary) -> {
                    /*
                          aptInstall retroarch "libretro-*"
                          mkdir -p "${HOME}/.config/retroarch/"
                          mkdir -p "${HOME}/BIOS"
                          ln -s "${HOME}/BIOS" "${HOME}/.config/retroarch/system"
                     */
                    return false;
                }));
        BINARIES.add(new Binary("kodi", false)
                .title("Kodi")
                .description("Movie player and library")
                .extraPackages("python3", "python3-dbus"));
        BINARIES.add(new Binary("zsh", false)
                .title("ZSH")
                .description("Improved command line environment")
                .post((Binary binary) -> {
                    /*
                        cargo install exa
                        chsh -s "$(which zsh)"
                        if [[ -h ${HOME}/.zshrc ]];
                        then
                          # Symbolic link
                          rm "${HOME}/.zshrc"
                        elif  [[ -f ${HOME}/.zshrc ]];
                        then
                          # Normal config file
                          mv "${HOME}/.zshrc" "${HOME}/.zshrc.bak"
                        fi
                        ln -s "${scriptDir}/config/zshrc" "${HOME}/.zshrc"
                     */
                    return false;
                }));
        BINARIES.add(new Binary("tilix", false)
                .title("Tilix")
                .description("Improved terminal emulator")
                .post((Binary binary) -> {
                    // gwinga
                    /*  if questionInstall "Tilix settings"
                        then
                          mkdir -p ~/.local/fonts/t
                          cp "${scriptDir}/../fonts/*" ~/.local/fonts/t
                          dconf load /com/gexperts/Tilix/ < "${scriptDir}/../config/tilix.dconf"
                        fi
                    */
                    return false;
                }));
        BINARIES.add(new Binary("onboard", false)
                .title("On-board")
                .description("On-screen keyboard"));
        BINARIES.add(new Binary("antimicrox", false)
                .title("AntiMicroX")
                .description("Bind gamepad to keyboard/mouse"));
        BINARIES.add(new Binary("htop", false)
                .title("htop")
                .description("Command line CPU and memory monitor")
                .fromGithub("AntiMicroX", "antimicrox"));
    }

    private Binaries() {
        super();
    }

    public static void checkBinaries() {
        for (Binary binary : BINARIES) {
            binary.checkInstalled();
        }
    }

    public static void printState() {
        for (Binary binary : BINARIES) {
            System.out.println(binary);
        }
    }
}
