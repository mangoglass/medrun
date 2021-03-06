/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The menu state is the state the game transitions to after the splash state,
 * here the user can enter different states of the game by pressing buttons
 * shown on the screen.
 *
 * @author Tom Axblad
 */
public class MenuState extends State {

    Input input;
    Button[] buttons;
    Image background;
    Image title;
    boolean mouseCheck;
    final float fontSize = 150;
    float gameMusicTime;
    public static final String menuMusicRef = "data/music/menumusic.aif";

    public MenuState(int stateID) {
        super(stateID);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Entered Menu State");
        if (Medrun.music != null) {
            if (!MusicPlayer.getRef().equals(menuMusicRef)) {
                if (MusicPlayer.getRef().equals(GameState.gameMusicRef)) {
                    gameMusicTime = MusicPlayer.getMusic().getPosition();
                }
                MusicPlayer.changeMusic(menuMusicRef);
            }
            MusicPlayer.play();
        }
        if (Camera.started && !GameState.player.dead) {
            buttons[4].setTitle("Continue");
        } else if (GameState.player.dead || !Camera.started) {
            sbg.getState(Medrun.GAME).init(gc, sbg);
            if (!buttons[4].getTitle().equals("Start")) {
                buttons[4].setTitle("Start");
            }
        } else if (!buttons[4].getTitle().equals("Start")) {
            buttons[4].setTitle("Start");
        }
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Left Menu State");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mouseCheck = false;
        background = new Image("data/sprites/layers/layer0.png");
        title = new Image("data/sprites/title.png");
        title.setFilter(Image.FILTER_NEAREST);
        Medrun.music = new MusicPlayer(menuMusicRef);
        buttons = new Button[]{new Button("Exit"), new Button("Settings"), new Button("Achievements"),
            new Button("Leaderboards"), new Button("Start")};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setY(Medrun.getHeight() - (buttons[i].getHeight() + 16) * (i + 1));
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0, Medrun.getWidth(), Medrun.getHeight());
        for (Button button : buttons) { // för alla button i arrayen buttons
            button.render(); // rendera button
        }
        Medrun.renderScaledCenter(title, Medrun.width / 2, 200, 1.5f);
        //Medrun.renderCenterdText(trueType, "MEDRUN", Medrun.width/2, 200, Color.white);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        input = gc.getInput();
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) { // If the player is holding the left mouse button down.
            mouseCheck = true;
            for (Button button : buttons) {
                if (button.isInButton(input.getMouseX(), input.getMouseY())) {
                    if (!button.isClicked()) {
                        button.togglePress();
                    }
                } else if (button.isClicked()) {
                    button.togglePress();
                }
            }
        } else if (mouseCheck) { // If the mouse have been clicked but we haven't checked whats been clicked yet.
            mouseCheck = false;
            for (Button button : buttons) {
                if (button.isClicked()) {
                    button.togglePress();
                    switch (button.getTitle()) {
                        case "Continue":
                            MusicPlayer.stop();
                            GameState.musicPos = gameMusicTime;
                            sbg.enterState(Medrun.PAUSE);
                            break;
                        case "Start":
                            MusicPlayer.stop();
                            sbg.enterState(Medrun.GAME);
                            break;
                        case "Leaderboards":
                            sbg.enterState(Medrun.LEADERBOARDS);
                            break;
                        case "Achievements":
                            sbg.enterState(Medrun.ACHIEVEMENTS);
                            break;
                        case "Settings":
                            sbg.enterState(Medrun.SETTINGS);
                            break;
                        case "Exit":
                            gc.exit();
                            break;
                        default:
                            System.out.println("Error in MenuState, entered button check, but no button is clicked.");
                            gc.exit();
                    }
                }
            }
        }
    }
}
