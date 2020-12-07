# Locomotive

## Goal

Create a handheld console capable of running PC games from multiple platforms.

## Supported platforms

* [Steam](https://store.steampowered.com/)
* GOG
* Humble Bundle
* Itch
* [Retroarch](https://www.retroarch.com/)
   * PSX
   * PSP
   * Nintendo DS
   * N64
   * ...

## Main design

The main goal of this project is for anyone to be able to build it.
It is possible to build a more compact handheld, but it would require more complex procedures:
* Custom PCBs would reduce cables, but are hard to produce.
* Custom batteries can better fit the available empty spaces, but Li-Po batteries are hard and dangerous (fire, explosion, ...) to build properly:
   * Over/under charge prevention
   * Charge balancing management
   * Heat monitoring
   * ...
* Un-soldering components can save some space, but over-heating other components can render the board unusable.
* ...
  
The design is also made to be able to evolve with time.
The different components can easily be swapped to equivalents based on availability, desire or future upgrades.

## List of parts

1. [LattePanda Alpha](https://www.lattepanda.com/)
   * Small form factor
   * Low power consumption
   * Intel 64 bits CPU
   * Intel GPU
1. Aluminium radiator case
   * To reduce the heat of the device during gaming
1. [UCTronics 7" screen](https://www.uctronics.com/display/uctronics-7-inch-touch-screen-for-raspberry-pi-1024-600-capacitive-hdmi-lcd-touchscreen-monitor-portable-display-for-pi-4-b-3-b-windows-10-8-7-free-driver.html)
   * HDMI + USB
1. [Asus Kunai 3](https://rog.asus.com/controllers/rog-kunai-3-gamepad-model/)
   * USB charging and function
   * Possible to use as a bluetooth controller as well

## Optional parts

1. Extra SSD
1. SD card

## Software

1. [Kubuntu](https://kubuntu.org)
   * Flexible and good-looking OS with low resource usage.
1. [Gamehub](https://github.com/tkashkin/GameHub)
   * A game library, able to handle multiple platforms transparently from one single place. 
1. [Retroarch](https://www.retroarch.com/) and [Libretro](https://www.libretro.com/)
   * An emulator manager and associated emulators.
1. [Wine](http://www.winehq.org/)
   * Compatibility layer to allow for Windows software to run on linux.
1. [Kodi](https://kodi.tv/)
   * A movie library
1. [Steam](https://store.steampowered.com/)
    * The official client for the game store
1. [Itch.io](https://itch.io)
    * The official client for the game store

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
