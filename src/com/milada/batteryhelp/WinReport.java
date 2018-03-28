package com.milada.batteryhelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class WinReport extends BatteryReport {
    //String name, description, caption, deviceID, health;
    //int capacity, voltage, rechargeTime, chargeRemaining, timeRemaining;

    private static final String WIN_COMMAND = "powershell Get-WmiObject Win32_Battery";

    WinReport() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(WIN_COMMAND).getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                try {
                    if (s.startsWith("Name")) name = s.split(Pattern.quote(":"))[1].trim();
                    if (s.startsWith("Description")) description = s.split(Pattern.quote(":"))[1].trim();
                    if (s.startsWith("Caption")) caption = s.split(Pattern.quote(":"))[1].trim();
                    if (s.startsWith("DeviceID")) deviceID = s.split(Pattern.quote(":"))[1].trim();
                    if (s.startsWith("Status") && !s.startsWith("StatusInfo")) health = s.split(Pattern.quote(":"))[1].trim();
                    if (s.startsWith("DesignCapacity")) capacity = Integer.parseInt(s.split(Pattern.quote(":"))[1].trim());
                    if (s.startsWith("DesignVoltage")) voltage = Integer.parseInt(s.split(Pattern.quote(":"))[1].trim());
                    if (s.startsWith("BatteryRechargeTime")) rechargeTime = Integer.parseInt(s.split(Pattern.quote(":"))[1].trim());
                    if (s.startsWith("EstimatedChargeRemaining")) chargeRemaining = Integer.parseInt(s.split(Pattern.quote(":"))[1].trim());
                    if (s.startsWith("EstimatedRunTime")) timeRemaining = Integer.parseInt(s.split(Pattern.quote(":"))[1].trim());
                } catch (Exception ex) {
                    //nothing
                }
            }
        } catch (NumberFormatException nfException) {
            System.err.println("Invalid integer detected, ignoring!");
        } catch (IOException ioException) {
            System.err.println("An error has occurred!");
            ioException.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException aioobException) {
            //Do nothing, these are annoying
        }
    }
}
