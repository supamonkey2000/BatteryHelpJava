package com.milada.batteryhelp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class BatteryHelp {
    static final int OS_WIN = 0, OS_MAC = 1, OS_NIX = 2; // OS codes
    private static final String WIN_COMMAND = "powershell Get-WmiObject Win32_Battery EstimatedChargeRemaining"; // DEPRECATED! Used for alpha testing
    private static final String MAC_COMMAND = "pmset -g batt"; // DEPRECATED! Used for reference in MacReport.java
    private static final String NIX_COMMAND = "cat /sys/class/power_supply/battery/capacity"; // DEPRECATED! Used for reference in NixReport.java

    private static int userOS; // DEPRECATED! Used for alpha testing

    /**
     * Main class when run from a terminal. Not really used for anything at this time
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        userOS = getOS();
    }

    /**
     * Used to return the user operating system
     * @return The OS code
     */
    static int getOS() {
        String OsString = System.getProperty("os.name").toLowerCase().trim();
        if (OsString.contains("windows")) return OS_WIN;
        if (OsString.contains("mac")) return OS_MAC;
        if (OsString.contains("nix")) return OS_NIX;
        else {
            System.exit(1);
            return -1;
        }
    }

    /**
     * @deprecated Please use BatteryReport class instead.
     * @return An integer for the battery level from 0 to 100
     */
    private static int getBatteryLevel() {
        switch (userOS) {
            case OS_WIN:
                return runWinCommand();
            case OS_MAC:
                return runMacCommand();
            case OS_NIX:
                return runNixCommand();
            default:
                return -1;
        }
    }

    /**
     * @deprecated Do not use
     * @return The result of the command
     */
    private static int runWinCommand() {
        try {
            //Runtime runtime = Runtime.getRuntime();
            //Process proc = Runtime.getRuntime().exec(MAC_COMMAND);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(WIN_COMMAND).getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.startsWith("EstimatedChargeRemaining")) return Integer.parseInt(s.split(Pattern.quote(":"))[1].trim());
            }
            return -1;
        } catch (Exception ex) {
            System.err.println("An error occurred!");
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * @deprecated Do not use
     * @return The result of the command
     */
    private static int runMacCommand() {
        try {
            //Runtime runtime = Runtime.getRuntime();
            //Process proc = Runtime.getRuntime().exec(MAC_COMMAND);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(MAC_COMMAND).getInputStream()));
            String s;
            while ((s = reader.readLine()) != null) {
                if (s.contains("-InternalBattery")) return Integer.parseInt( s.trim().split(Pattern.quote("-InternalBattery"))[1].trim().split(Pattern.quote("%"))[0]);
            }
            return -1;
        } catch (Exception ex) {
            System.err.println("An error occurred!");
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * @deprecated Do not use
     * @return The result of the command
     */
    private static int runNixCommand() {
        try {
            //Runtime runtime = Runtime.getRuntime();
            //Process proc = Runtime.getRuntime().exec(NIX_COMMAND);
            //BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(NIX_COMMAND).getInputStream()));
            //String s = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(NIX_COMMAND).getInputStream())).readLine();
            return Integer.parseInt(new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(NIX_COMMAND).getInputStream())).readLine());
        } catch (Exception ex) {
            System.err.println("An error occurred!");
            ex.printStackTrace();
            return -1;
        }
    }
}
