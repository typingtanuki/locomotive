# Locomotive

## Goal

Create a handheld console capable of running PC games from multiple platforms.

## Supported platforms

* [Steam](https://store.steampowered.com/)
* [GOG](https://www.gog.com/)
* [Humble Bundle](https://www.humblebundle.com/)
* [Itch](https://www.itch.io/)
* [Retroarch](https://www.retroarch.com/)
    * PSX
    * PSP
    * Nintendo DS
    * N64
    * ...

## Main design

The main goal of this project is for anyone to be able to build it.
While it is possible to build a more compact handheld, it would require more complex procedures and tools:

* Custom PCBs would reduce cables, but are hard to source and produce.
* Custom batteries can better fit the available empty spaces, but Li-Po batteries are hard and dangerous (fire,
  explosion, ...) to build properly:
    * Over/under charge prevention
    * Charge balancing management
    * Heat monitoring
    * ...
* Un-soldering components can save some space, but over-heating other components can render the board unusable.
* ...

The design is also meant to be able to evolve. The different components can easily be swapped to equivalents
based on availability, desire or future upgrades.

## List of parts

1. [LattePanda Alpha](https://www.lattepanda.com/)
    * Small form factor
    * Low power consumption
    * Intel 64 bits CPU
    * Intel GPU
1. [UCTronics 7" screen](https://www.uctronics.com/display/uctronics-7-inch-touch-screen-for-raspberry-pi-1024-600-capacitive-hdmi-lcd-touchscreen-monitor-portable-display-for-pi-4-b-3-b-windows-10-8-7-free-driver.html)
    * HDMI + USB
1. [Asus Kunai 3](https://rog.asus.com/controllers/rog-kunai-3-gamepad-model/)
    * USB charging and function
    * Possible to use as a bluetooth controller as well
1. Small HDMI connector
1. Small micro-USB to USB-A cable
1. Small USB-C (female) to USB-A cable (male)
1. Power button for AT/X motherboard
1. Power adapter for latte panda alpha
1. LiPo Battery
1. PCB spacers

## Optional parts

1. Extra SSD
1. Micro SD card
1. USB hub

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
1. [On-board](https://launchpad.net/onboard)
    * An on-screen keyboard
1. [Antimicrox](https://github.com/AntiMicroX/antimicroX)
    * Bind gamepad controls to keyboard/mouse

## Testing the parts

Before starting to build, let's test and prepare the different parts

1. Screen
    1. Plug the USB cable in the "Power + touch" port of the screen
    1. Plug the HDMI cable in the HDMI port of the screen
1. LattePanda alpha
    1. Plug both antennas (small wires) for bluetooth and Wi-Fi (see LattePanda Alpha documentation)
    1. Plug the other end of the HDMI cable in the HDMI port of the LattePanda Alpha
    1. Plug the other end of the screen's USB cable to the LattePanda Alpha
    1. Make sure the LattePanda Alpha is flat, and the fan is unobstructed
1. Powering up
    1. Plug the AC adapter to the electric plug
    1. Plug the USB type C cable to the LattePanda Alpha
        * A red light will start blinking on the LattePanda
        * After a while a blue light will also turn on
    1. Wait for the red light on the LattePanda to stay on and the blue light blinking at regular interval
    1. Press the power button for a few seconds, until the blue light turns and stays on

> **NOTE**: It may take a while for the screen and LattePanda to finish their handshake.

In the meanwhile you may see:

* The UCTronics logo (from the screen)
* The LattePanda logo
* The windows loading screen
* An error page (if the LattePanda came without OS)

If you have reached this point, your LattePanda and your screen are functional.

Now turn off your LattePanda by pressing the power button for a few seconds.

## Putting it all together

### Installing the SSD

> **NOTE**: Make sure to have unplugged the power from the LattePanda Alpha

> **NOTE**: There are several sizes of SSDs. Shorter ones will require an adapter in order to be used with the LattePanda Alpha.

> **NOTE**: There are several types of SSDs. Make sure to check the LattePanda Alpha documentation to pick a compatible one.

1. Unscrew the silver screw on the back of the LattePanda facing the SSD port
1. Slide the SSD in the port
    * It will slide without effort, at a slight angle
1. Push the SSD down against the board and screw it

### Install the micro SD card

> **NOTE**: The LattePanda Alpha supports to up to 2TB SD cards

1. Locate the micro SD slot on the back side of the LattePanda Alpha
1. Gently push the micro SD card in the slot
    * It should lock with a click

### Setting up the gamepad

1. Set the LattePanda Alpha upside down
1. Screw PCB spacers on the bottom side of the LattePanda Alpha
1. Set the bumper (the phone case) centered on top of the spacers
    * Make sure it is the outer side of the bumper touching the PCB spacers
    * Make sure to have the USB-C plug on the bumper on the side of the USB-A ports on the LattePanda Alpha
1. Mark the position where the bumper touches the PCB spacers
1. Make small holes in the bumper on the marks you have just made
1. Screw the bumper on the PCB spacers
1. Connect the USB-C (female) to USB-A (male) cable to the bumper's plug
1. Pass the cable in the hole in the bumper
1. Plug the cable to the LattePanda Alpha USB-A plug 

###  Setting up the screen

TBD

### Installing the OS

TBD

### Installing the software

1. Install git and java

```bash
sudo apt install git git-lfs openjdk-11-jre
```

2. Clone repository

```bash
cd ~
git clone --recursive git@github.com:typingtanuki/locomotive.git .locomotive
```

3. Start the setup wizard

```bash
~/.locomotive/install.sh
```

## Maintenance

### Changing the SSD

TBD

### Updating

1. Update the repository

```bash
cd ~/.locomotive
git pull --rebase
```

2. Re-run the installer

```bash
~/.locomotive/install.sh
```
