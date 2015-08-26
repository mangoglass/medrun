/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The Achieve state is a state linked to the MenuState. Here the user will be
 * able to look at all the unlocked achievements.
 *
 * @author Tom Axblad
 */
public class AchieveState extends State {

    boolean hundredM;
    boolean fivehundredM;
    boolean thousandM;
    boolean highscore;
    boolean barellroll;
    boolean trick;
    String ref;

    public AchieveState(int stateID) {
        super(stateID);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        hundredM = false;
        fivehundredM = false;
        thousandM = false;
        highscore = false;
        barellroll = false;
        trick = false;
        ref = "data/achieve.ini";
        try {
            readAchievementsFile();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    public void update() {

    }

    public void readAchievementsFile() throws FileNotFoundException, IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ref));
            String line = reader.readLine();
            while (line != null) {
                if (line.startsWith("bHundredM")) {
                    switch (line.substring(line.indexOf("=") + 1).toLowerCase().trim()) {
                        case "true":
                            hundredM = true;
                            break;
                        case "false":
                            hundredM = false;
                            break;
                        default:
                            System.out.println("");
                            hundredM = false;
                            break;
                    }
                } else if (line.startsWith("bHundredM")) {
                    switch (line.substring(line.indexOf("=") + 1).toLowerCase().trim()) {
                        case "true":
                            hundredM = true;
                            break;
                        case "false":
                            hundredM = false;
                            break;
                        default:
                            System.out.println("");
                            hundredM = false;
                            break;
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("creating new achievement file");
            try {
                writeAchievementsFile();
            } catch (Exception i) {
                System.out.println(i.getMessage());
            }
        }
    }

    public void writeAchievementsFile() {
        Writer writer = null;
        try {
            File f = new File(ref);
            f.getParentFile().mkdirs();
            f.createNewFile();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f.getPath()), "utf-8"));
            writer.write("bHundredM = false\n" + "bFivehundredM = false\n" + "bThousandM = false\n"
                    + "bHighscore = false\n" + "bBarellroll = false\n" + "bTrick = false");
        } catch (IOException e) {

        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void writeAchievementsFile(String achievement) {

    }

}
