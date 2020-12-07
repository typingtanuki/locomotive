#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source ${scriptDir}/common.sh

title "Packages - ZSH"
subtitle "Improved command line environment"

if installed "zsh";
then
  echo "ZSH already installed"
else
  if questionInstall "ZSH and dependencies";
  then
    aptInstall zsh libnotify-bin fzf fzy peco cargo
    cargo install exa
    chsh -s `which zsh`
  fi
fi

if installed "exa";
then
  echo "Exa already installed"
else
  if questionInstall "Exa";
  then
    cargo install exa
  fi
fi

title "Packages - Required packages"
subtitle "Useful packages required by the tools"

installTool "dconf-cli" "dconf" "dconf"
installTool "htop"
installTool "curl"
installTool "wget"
installTool "build-essentials" "Build essentials" "gcc"
installTool "dos2unix"

title "Packages - Retroarch"
subtitle "Emulator manager and emulators (libretro based)"

if installed "retroarch";
then
  echo "Retroarch already installed"
else
  if questionInstall "Retroarch and dependencies";
  then
      aptInstall retroarch "libretro-*"
  fi
fi

title "Packages - Kodi (movies/...)"
subtitle "Movie library"

if installed "kodi";
then
  echo "Kodi already installed"
else
  if questionInstall "Kodi";
  then
    aptInstall kodi python3 python3-dbus
  fi
fi

title "Packages - Steam"
subtitle "The Steam client for linux"

if installed "steam";
then
  echo "Steam already installed"
else
  if questionInstall "Steam";
  then
    aptInstall steam libgtk2.0-0:i386 libxtst6:i386
  fi
fi

title "Packages - Wine"
subtitle "Allows running Windows software, games, ..."

if installed "wine";
then
  echo "Wine already installed"
else
  if questionInstall "Wine";
  then
    aptInstall --install-recommends winehq-staging
  fi
fi

title "Packages - GameHub"
subtitle "Game library for managing steam, GOG, ... and emulators in a single GUI"

if installed "gamehub";
then
  echo "Gamehub already installed"
else
  if questionInstall "Gamehub";
  then
    aptInstall xcb icoutils gamehub
  fi
fi

kwriteconfig5 --file ~/.config/kwinrc --group ModifierOnlyShortcuts --key Meta "org.kde.plasmashell,/PlasmaShell,org.kde.PlasmaShell,activateLauncherMenu"
qdbus org.kde.KWin /KWin reconfigure
