#!/bin/bash
set -e

wget -O - https://dl.winehq.org/wine-builds/winehq.key | sudo apt-key add -
curl -s https://brave-browser-apt-release.s3.brave.com/brave-core.asc | sudo apt-key --keyring /etc/apt/trusted.gpg.d/brave-browser-release.gpg add -

sudo dpkg --add-architecture i386 # 32 bit compat
sudo add-apt-repository multiverse # Extra tools
sudo apt-add-repository ppa:kubuntu-ppa/ppa -y # Kubuntu updates
sudo apt-add-repository ppa:kubuntu-ppa/backports -y # KDE main updates
sudo apt-add-repository ppa:ubuntu-x-swat/updates -y # X & graphics updates
sudo add-apt-repository 'deb https://dl.winehq.org/wine-builds/ubuntu/ focal main' -y
sudo add-apt-repository ppa:lyzardking/ubuntu-make -y
sudo add-apt-repository ppa:tkashkin/gamehub -y # Gamehub
echo "deb [arch=amd64] https://brave-browser-apt-release.s3.brave.com/ stable main" | sudo tee /etc/apt/sources.list.d/brave-browser-release.list

sudo apt-get update

sudo apt install --install-recommends winehq-staging -y
