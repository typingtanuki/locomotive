#!/bin/bash
set -e

source common

if installed "fcitx";
then
  echo "Keyboard already setup"
else
  echo "Setting up keyboard"
  doInstall fcitx fcitx-mozc fcitx-frontend-gtk2 fcitx-frontend-gtk3 fcitx-frontend-qt5 fcitx-ui-classic kde-config-fcitx mozc-utils-gui fcitx-tools
  fcitx-autostart
  im-config -n fcitx
  fcitx-configtool
fi

if installed "update";
then
    echo "Script already exists"
else
  echo "Creating update script"
  sudo ln -s ${scriptDir}/scripts/update /usr/bin/update
  sudo chmod a+rx /usr/bin/update
fi

installTool "git"
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
    chsh -s $(which zsh)
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


if installed "tilix";
then
  echo "Tilix already installed"
else
  if question "tilix";
  then
    doInstall tilix
    if question "Tilix settings"
    then
      # dconf dump /com/gexperts/Tilix/ > ~/setup/tilix.dconf
      dconf load /com/gexperts/Tilix/ < ~/setup/tilix.dconf
    fi
  fi
fi

installTool "htop"
installTool "curl"
installTool "wget"
installTool "geany"
installTool "vlc"
installTool "remmina"
installTool "build-essentials" "Build essentials" "gcc"
installTool "dos2unix"

installTool "steam"

if installed "retroarch";
then
  echo "Retroarch already installed"
else
  doInstall retroarch "libretro-*" -y
fi

if installed "kodi";
then
  echo "Kodi already installed"
else
  doInstall kodi python3 python3-dbus -y
fi

kwriteconfig5 --file ~/.config/kwinrc --group ModifierOnlyShortcuts --key Meta "org.kde.plasmashell,/PlasmaShell,org.kde.PlasmaShell,activateLauncherMenu"
qdbus org.kde.KWin /KWin reconfigure
