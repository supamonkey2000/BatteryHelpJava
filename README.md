# BatteryHelpJava
A battery report and diagnostic library for Java

## What is this for?

While I was working on a benchmark software in Java, I realized during the laptop portion that Java has no battery statistics API. I decided to make my own. This library can support Windows, and (coming soon) Mac and Linux.

## How does it work?

It uses the command line battery commands for each individual OS. Unfortunately, some stats may not be available on all systems. Despite this, the most useful stat (charge level) is available across all OS's. As long as your system has terminal functions, then you can use this library.

## What can I use it for?

Literally anything you want, as long as you credit me in some way (linking my GitHub page would be nice).

## How do I use it?

To generate a new battery report, you can use the following line of code:

    BatteryReport batteryReport = new BatteryReport().generateReport();

After that has been run, you can access the following variables (provided the system gave a result in the report):

Strings:

- `name`
- `description`
- `caption`
- `deviceID`
- `health`

Integers:

- `capacity`
- `voltage`
- `rechargeTime`
- `chargeRemaining`
- `timeRemaining`

These are retrieved by calling `batteryReport.name`, for example.

For now, only Windows works as I do not own a Mac and I have not finished Linux. Even though I am working on those, if you want to contribute to this project, I would really appreciate it!