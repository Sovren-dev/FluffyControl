# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/)

## 0.10.0]

### Changed
- Moved warnSystem into it's own class called RestrictionManager. 
- Changed networkController to be less redundant. 
- GUI now uses SwingUtilities.invokeLater

## [0.9.0]

### Added
- Automatic Ethernet/Wi-Fi disconnection and reconnection based on the current time of day.
- Added the `NetworkController` class to handle Ethernet/Wi-Fi disconnection and reconnection.

### Removed
- Unused code.

## [0.8.1]

### Added
- Added additional games to the `rngGames` feature.

### Changed
- Removed unused activity buttons.
- Reworked the warning system.
- Warnings are now only displayed when the user has passed the configured end of day time.

## [0.8.0]

### Platform Change
- Development has moved from Windows to Linux
- Windows support has been discontinued as of this release.

### Added
- Added a semi-fullscreen warning when the user is outside the configured time period and when bedtime is reached.

### Removed
- Password lock.
- Automatic shutdown.
- Profiles.
- `openFromJar`.

## [0.7.0-Windows]

### Added
- Added **Physical**, **Low Effort**, and **Activities** buttons.

### Changed
- The program now detects three different time frames.
- When the user is within a configured time frame, a pop-up displays how much time remains until the PC should be turned off.
- When the user is outside the configured time frames, a pop-up instructs the user to turn off the PC.
- Added `DurationLeft()`, which calculates the remaining time in the current time frame and displays it in a pop-up.

## [0.6.1-Windows]

### Changed
- The system tray icon can now be left clicked to show or hide the user interface.

## [0.6.0-Windows]

### Added
- Added a basic user interface.
- Added a random game option.
- Added an output area.
- Added a password protected quit button.
- Added a reset password button.

## [0.5.0-Windows]

### Added
- Added password protected quitting as a deterrent against accidentally or intentionally closing the program.
- The program generates a temporary password file and stores the password as a string for the quit function to reference.
- Entering `quit` prompts the user for the password.
- The program exits when the entered password matches the most recently generated password.
- Added `new_password` to generate a new password if the temporary password file is lost.

### Removed
- Unused tasks and rewards code.

## [0.4.0-Windows]

### Added
- Added a feature preventing the user from closing the program 30 minutes before the automatic shutdown time.
