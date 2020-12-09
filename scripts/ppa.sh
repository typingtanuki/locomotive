#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source "${scriptDir}/common.sh"
parseArguments "$@"
setupLogFile "ppa"

welcome "This wizard will help you set up additional PPAs" "Repositories of packages and updates for the system"

 #############
## Core PPAs ##
 #############
title "PPA - Core compatibility"
subtitle "32bit compatibility, and extra updates"

sudo dpkg --add-architecture i386 # 32 bit compat
ppaInstall multiverse # Extra tools
ppaInstall ppa:kubuntu-ppa/ppa # Kubuntu updates
ppaInstall ppa:kubuntu-ppa/backports # KDE main updates

 ########
## Wine ##
 ########
title "PPA - Wine"
subtitle "Allows running Windows software, games, ..."

ppaAddKey 'winehq.org' 'https://dl.winehq.org/wine-builds/winehq.key'
ppaInstall 'deb https://dl.winehq.org/wine-builds/ubuntu focal main'

 #######
## GPU ##
 #######
title "PPA - GPU"
subtitle "Up to date drivers for intel GPUs and X (graphical) server"

ppaInstall ppa:ubuntu-x-swat/updates # X & graphics updates
ppaInstall ppa:oibaf/graphics-drivers # Intel graphics drivers

 ###########
## GameHub ##
 ###########
title "PPA - Gamehub"
subtitle "Game library, with compatibility with multiple sources"

ppaInstall ppa:tkashkin/gamehub # Gamehub

 ##########
## Update ##
 ##########
title "PPA - APT update"
subtitle "Update system to be ready to use the new repositories"

aptUpdate

