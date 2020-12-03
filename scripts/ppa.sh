#!/bin/bash
set -e

scriptDir=$(dirname "$(readlink -f $0)")
source ${scriptDir}/common

echo "Enabling extra compatibility repositories"
sudo dpkg --add-architecture i386 # 32 bit compat
sudo add-apt-repository multiverse # Extra tools
sudo apt-add-repository ppa:kubuntu-ppa/ppa -y # Kubuntu updates
sudo apt-add-repository ppa:kubuntu-ppa/backports -y # KDE main updates

echo "Enabling wine repository"
wget -O - https://dl.winehq.org/wine-builds/winehq.key | sudo apt-key add -
sudo add-apt-repository 'deb https://dl.winehq.org/wine-builds/ubuntu/ focal main' -y

echo "Enabling GPU updates repositories"
sudo apt-add-repository ppa:ubuntu-x-swat/updates -y # X & graphics updates
sudo add-apt-repository ppa:oibaf/graphics-drivers # Intel graphics drivers

echo "Enabling gamehub repository"
sudo add-apt-repository ppa:tkashkin/gamehub -y # Gamehub

sudo apt update
sudo apt upgrade -y -f
sudo apt dist-upgrade -y -f
sudo apt autoremove -y
sudo apt autoclean -y
