# FluffyControl

Java application design to help me keep a healthy sleep schedule. With some extra experimental features.
It alerts user when it's time to head to bed or turn of the computer. It will automatically disable users network connection to prevent late night browsing. When daylight returns it automatically restores the connection

## Main Features
- **Visual Reminder**: Opens a warning window when it's time to turn of the computer.
- **Automated Disconnection**: Disables a specified network interface (Ethernet but can be configured to change Wi-Fi instead) using `nmcli`.
- **Automatic Reconnection**: Restores the internet connection automatically in the morning.

## How It Works

By default, the program enforces the following schedule:
- **Off-hours (Bedtime)**: `23:30` to `08:00` (Network disabled + Warning popup)
- **On-hours (Daytime)**: `08:00` to `23:30` (Network enabled)

## Requirements

- **Operating System**: Linux
- **Network Manager**: `NetworkManager` must be installed and running

## Configuration

Before running the application, make sure to set your correct network interface name in the code:
```java
private static final String INTERFACE_NAME = "enp14s0"; // or "eth0", "wlan0", etc.
```
*You can find your active network interface name by running `nmcli device` in your terminal.*

## Experimental features

- **rngGame**: 
