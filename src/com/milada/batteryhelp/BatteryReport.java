package com.milada.batteryhelp;

public class BatteryReport {
    String name, description, caption, deviceID, health;
    int capacity, voltage, rechargeTime, chargeRemaining, timeRemaining;

    BatteryReport generateReport() {
        switch (BatteryHelp.getOS()) {
            case BatteryHelp.OS_WIN:
                return new WinReport();
            case BatteryHelp.OS_MAC:
                return new MacReport();
            case BatteryHelp.OS_NIX:
                return new NixReport();
            default:
                return null;
        }
    }
}
