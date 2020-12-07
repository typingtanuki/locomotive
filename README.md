# locomotive

## Goal

Create a handheld console capable of running PC games.

## Supported platforms

* Steam
* GOG
* Humble Bundle
* Itch
* Retroarch
   * PSX
   * PSP
   * Nintendo DS
   * N64
   * ...

## Main design

* Easy to build for anyone:
   * No custom PCBs
   * No custom batteries
* Evolutive design:
   * Use parts which can be easily interchanged
   * Do not rely on proprietary format
    
## List of parts

1. LattePanda Alpha
   * Small form factor
   * Low power consumption
   * Intel 64 bits CPU
   * Intel GPU
1. LattePanda accessories
1. Aluminium radiator case
1. UCTronics 7" screen
   * HDMI + USB
    
## Software

1. Kubuntu
1. Gamehub
1. Steam
1. Retroarch
1. Wine
1. Kodi

## Installing

1. Install git

```bash
sudo apt install git git-lfs
```

2. Clone repository

```bash
cd ~
git clone git@github.com:typingtanuki/locomotive.git .locomotive
```

3. Start the setup wizard

```bash
~/.locomotive/install.sh
```

## Updating

1. Update the repository

```bash
cd ~/.locomotive
git pull --rebase
```

2. Re-run the install

```bash
~/.locomotive/install.sh
```
