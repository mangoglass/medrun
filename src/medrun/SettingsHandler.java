/*
 * Copyright (C) 2015 Tom Axblad
 *
 * This game is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This game is distributed in the hope that it will be entertaining,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package medrun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * The settings Handler creates the settings file if none has been created or
 * reads different settings if one has been created.
 *
 * @author Tom
 */
public class SettingsHandler {

    BufferedReader reader;
    public final String ref;
    public boolean streamIsOpen;

    /**
     * Constructor of the Settings handler.
     *
     * @param ref The location reference which the settings file will be read
     * from, or written in.
     * @throws java.io.FileNotFoundException
     */
    public SettingsHandler(String ref) throws FileNotFoundException, IOException {
        this.ref = ref;
        try {
            reader = new BufferedReader(new FileReader(ref));
            System.out.println("Settings file existed in " + ref);
        } catch (FileNotFoundException e) {
            writeNewFile();
            reader = new BufferedReader(new FileReader(ref));
            System.out.println("Settings file in " + ref + " did not exist, creating now.");
        }
        streamIsOpen = true;
        reader.mark(4000);
    }

    private void writeNewFile() {
        File f = new File(ref);
        f.getParentFile().mkdirs();
        Writer writer = null;
        try {
            f.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(f.getPath()), "utf-8"));
            writer.write("\n\n# Video Settings:");
            writer.write("\n");
            writer.write("bFullScreen = false\n");
            writer.write("bVSync = true\n");
            writer.write("bDisplayFps = false\n");
            writer.write("iWidth = " + Medrun.displayWidth + "\n");
            writer.write("iHeight = " + Medrun.displayHeight + "\n");
            writer.write("iTargetFramerate = 60\n");
            writer.write("iMinimumLogicUpdateInterval = 10\n");
            writer.write("\n\n# Game Settings:");
            writer.write("\n");
            writer.write("# 1 = easy, 2 = medium, 3 = hard\n");
            writer.write("iDifficulty = 1");
            writer.write("\n\n# Sound Settings:");
            writer.write("\n");
            writer.write("bSMute = false\n");
            writer.write("iSMaster = 100\n");
            writer.write("iSEffects = 100\n");
            writer.write("iSMusic = 100\n");
        } catch (IOException i) {
            System.err.println("Caught IOException: " + i.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                System.out.println("Error! Failed to close writer in SettingsHandler!\n" + ex.toString());
                Medrun.app.exit();
            }
        }
    }

    /**
     * Reads an Integer setting from the settings file.
     *
     * @param setting The setting to be found in the file.
     * @return Returns the setting as a true int.
     * @throws java.io.IOException
     */
    public int readIntSetting(String setting) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            if (line.startsWith(setting)) {
                reader.reset();
                return intHandler(line);
            }
            line = reader.readLine();
        }
        System.out.println("Error in readIntSetting for setting;" + setting + ". Setting not found in settings file!");
        Medrun.app.exit();
        return 0;
    }

    /**
     * Reads a boolean setting from the settings file
     *
     * @param setting The setting to be found in the file.
     * @return Returns the setting as a true boolean.
     * @throws IOException
     */
    public boolean readBolSetting(String setting) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            if (line.startsWith(setting)) {
                reader.reset();
                return bolHandler(line);
            }
            line = reader.readLine();
        }
        System.out.println("Error in readBolSetting for setting;" + setting + ". Setting not found!");
        Medrun.app.exit();
        return true;
    }

    private boolean bolHandler(String inputText) {
        switch (inputText.substring(inputText.indexOf("=") + 1).toLowerCase().trim()) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                System.out.println("error in settings.ini\n" + inputText.substring(0, inputText.indexOf("=") - 1) + " holds invalid value, only \"true\" or \"false\" is allowed.\nreturning false");
                return false;
        }
    }

    private int intHandler(String inputText) {
        return Integer.parseInt(inputText.substring(inputText.indexOf("=") + 1).toLowerCase().trim());
    }

    /**
     * Closes the buffered reader stream.
     */
    public void closeReader() {
        if (streamIsOpen) {
            try {
                reader.close();
                streamIsOpen = false;
            } catch (Exception e) {
                System.out.println("Error! Failed to close reader in SettingsHandler!\n" + e.toString());
                Medrun.app.exit();
            }
        }
    }

    /**
     * Opens the buffered reader stream if it's closed.
     */
    public void openReader() {
        if (!streamIsOpen) {
            try {
                reader = new BufferedReader(new FileReader(ref));
                streamIsOpen = true;
            } catch (FileNotFoundException e) {
                System.out.println("Error! Failed to open reader in SettingsHandler!\n" + e.toString());
                Medrun.app.exit();
            }
        }
    }
}
