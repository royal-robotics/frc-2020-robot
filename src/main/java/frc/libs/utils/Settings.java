package frc.libs.utils;

import java.io.*;
import java.math.*;
import java.nio.file.*;
import java.util.*;

import edu.wpi.first.wpilibj.*;

public final class Settings {
    public static void save(String name, double value) {
        final var settingsFile = getSettingsFile(name);
        try {
            try (final var fstream = new FileWriter(settingsFile, false)) {
            try (final var out = new BufferedWriter(fstream)) {
                out.write(serializeDouble(value));
            }}

        } catch (IOException e) {
            System.out.printf("Warning: Failed to save %s with value %.2f\n", name, value);
        }
    }

    public static double loadDouble(String name, double defaultValue) {
        final var settingsFile = getSettingsFile(name);
        try {
            try(final var settingsReader = new Scanner(settingsFile)) {
                if (!settingsReader.hasNextDouble()) {
                    System.out.printf("Warning: Corrupt settings file for %s\n", name);
                    return defaultValue;
                }

                return settingsReader.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Warning: No settings file for %s\n", name);
            return defaultValue;
        }
    }

    private static String serializeDouble(double value) {
        final var bigDecimal = BigDecimal.valueOf(value).setScale(4, RoundingMode.HALF_UP);
        return bigDecimal.toPlainString();
    }

    private static File getSettingsFile(String fileName) {
        final var settingsDirectory = getSettingsDirectory();
        final var settingsFileName = Paths.get(settingsDirectory.getAbsolutePath(), fileName);
        return settingsFileName.toFile();
    }

    private static File getSettingsDirectory() {
        final var settingsDirectoryName = Paths.get(Filesystem.getOperatingDirectory().getAbsolutePath(), "settings");
        final var settingsDirectory = settingsDirectoryName.toFile();
        if (!settingsDirectory.exists()) {
            settingsDirectory.mkdirs();
        }
        return settingsDirectory;
    }
}
