#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source ${scriptDir}/common

installTool "fzf"
installTool "fzy"
installTool "peco"
installTool "cargo"
installTool "dconf-cli" "dconf" "dconf"

if installed "zsh";
then
  echo "ZSH already installed"
else
  if question "ZSH and dependencies";
  then
    doInstall zsh libnotify-bin
    cargo install exa
    chsh -s `which zsh`
  fi
fi

if installed "exa";
then
  echo "Exa already installed"
else
  if question "Exa";
  then
    cargo install exa
  fi
fi

installTool "htop"
installTool "curl"
installTool "wget"
installTool "build-essentials" "Build essentials" "gcc"
installTool "dos2unix"

if installed "retroarch";
then
  echo "Retroarch already installed"
else
  doInstall retroarch "libretro-*"  -y
fi

if installed "kodi";
then
  echo "Kodi already installed"
else
  doInstall kodi python3 python3-dbus -y
fi

if installed "steam";
then
  echo "Steam already installed"
else
  doInstall steam libgtk2.0-0:i386 libxtst6:i386 -y
fi

if installed "wine";
then
  echo "Wine already installed"
else
  doInstall --install-recommends winehq-staging -y
fi

if installed "gamehub";
then
  echo "Gamehub already installed"
else
  doInstall xcb icoutils gamehub
fi

kwriteconfig5 --file ~/.config/kwinrc --group ModifierOnlyShortcuts --key Meta "org.kde.plasmashell,/PlasmaShell,org.kde.PlasmaShell,activateLauncherMenu"
qdbus org.kde.KWin /KWin reconfigure
