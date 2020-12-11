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
1. [Aluminium alloy armor](https://geekworm.com/products/aluminum-alloy-armor-passive-cooling-case-for-lattepanda-alpha-board)
   * To reduce the heat of the device during gaming
1. [UCTronics 7" screen](https://www.uctronics.com/display/uctronics-7-inch-touch-screen-for-raspberry-pi-1024-600-capacitive-hdmi-lcd-touchscreen-monitor-portable-display-for-pi-4-b-3-b-windows-10-8-7-free-driver.html)
   * HDMI + USB
1. [Asus Kunai 3](https://rog.asus.com/controllers/rog-kunai-3-gamepad-model/)
   * USB charging and function
   * Possible to use as a bluetooth controller as well
1. HDMI connector
1. Power adapter for latte panda alpha

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
1. [Tilix](https://gnunn1.github.io/tilix-web/)
    * A very nice terminal emulator
1. [Melso](https://github.com/andreberg/Meslo-Font)
    * A very complete font for terminals
1. [ZSH](https://www.zsh.org)
    * A good shell, with very helpful autocomplete
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

### Removing the original fan

> **NOTE**: Make sure to have unplugged the power from the LattePanda Alpha

> **NOTE**: Skip this step if you are not going to install the aluminium heat sink. Do not start the LattePanda Alpha without any fan.

1. Follow the fan cable and unplug it. Make sure to remember where it was plugged
1. Unscrew the 4 black screws on the back-side of the LattePanda
1. Gently pull the fan off the board
1. Using tissues, remove the thermal paste from the CPU
   * Do it slowly and make sure not to put any on the board itself

### Installing the SSD

> **NOTE**: This step is easier done before setting the aluminium cover as it blocks the back-side of the LattePanda. If you wish to add an SSD later or if you forgot this step, please refer to the section "Changing the SSD"

> **NOTE**: Make sure to have unplugged the power from the LattePanda Alpha

> **NOTE**: There are several sizes of SSDs. Shorter ones will require an adapter in order to be used with the LattePanda Alpha.

1. Unscrew the silver screw on the back of the LattePanda facing the SSD port
2. Slide the SSD in the port
   * It will slide without effort, at a slight angle
3. Push the SSD down and screw it

### Installing the aluminium armor

> **NOTE**: Make sure to have unplugged the power from the LattePanda Alpha

1. Put new thermal paste on the CPU of the LattePanda
   * Using the syringe, put a small quantity of thermal paste on the CPU of the LattePanda, make sure not to put too much or it will spill when pressed
1. Place the copper piece on top of the thermal paste and press slightly
   * Fresh thermal paste is slippery, be careful when pressing
1. Put new thermal paste on the copper piece
   * Using the syringe, put a small quantity of thermal paste over the copper piece, make sure not to put too much or it will spill when pressed
1. Put the top aluminium cover
   * Make sure to check the position of the USB and ethernet ports correspond well
1. Carefully turn over the LattePanda Alpha
   * Keep pressure on the aluminium cover for it not to slip and for the copper piece not to slip as well
1. Put the bottom aluminium cover
1. Screw the 4 screws of the cover
1. Turn the LattePanda back up
1. Place the new fan over the space on the top cover
   * The space is difficult to see, put you can see the shape of the 4 screws of the fan in the aluminium
1. Screw the 4 screws of the fan
1. Plug the fan
   * Put it back in the same place you unplugged the original fan from
   * > **NOTE**: This plug does not have the words "SW" written on it

### Extracting the gamepad connector

Get the bumper (the phone case) and:
1. Unscrew the 3 screws holding each of the connector or either side of the bumper
1. Remove the plastic covers (keep them)
1. Carefully remove the tape from the inner side of the bumper to expose the ribbon-cable under
1. On the side **without** the USB connector:
   1. Find the connector linking the ribbon-cable you have just exposed to the gamepad connector
   1. Open the connector by gently lifting the black part away from the PCB
   1. Remove the connector
1. Slide the other gamepad connector out of the bumper, with the ribbon-cable still attached
1. Plug back the ribbon-cable into the connector and push back the black lift on the connector

> **NOTE**: If the ribbon-cable is still sticky due to the glue of the bumper, stick it onto some piece of paper to avoid damaging the ribbon.

### Wiring it all up

TBD

### Installing the OS

TBD

### Installing the software

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

## Maintenance

### Changing the SSD

TBD

### Updating

1. Update the repository

```bash
cd ~/.locomotive
git pull --rebase
```

2. Re-run the install

```bash
~/.locomotive/install.sh
```
