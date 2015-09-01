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
 * This state contains the settings menu that the user will be able to use to
 * change the different settings in the game.
 *
 * @author Tom Axblad
 */
public class SettingsState extends State {

    private static final int RESOLUTION = 0;
    private static final int FRAMERATE = 1;
    private static final int DIFFICULTY = 2;
    private static final int MASTERVOLUME = 3;
    private static final int EFFECTSVOLUME = 4;
    private static final int MUSICVOLUME = 5;
    private static final int NONE = 6;
    private static final int HeightAjustment = -70;

    private Image background;
    private boolean mouseCheck;
    private Image title;
    private Button goBack;
    private Slider[] sliders;
    int clickedSlider;
    Input input;

    public SettingsState(int stateID) {
        super(stateID);
        this.clickedSlider = NONE;
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Entered Settings State");
        if (!Medrun.settings.streamIsOpen) {
            Medrun.settings.openReader();
        }
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Left Settings State");
        if (Medrun.settings.streamIsOpen) {
            Medrun.settings.closeReader();
        }
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mouseCheck = false;
        background = new Image("data/sprites/layers/layer0.png");
        title = new Image("data/sprites/settings.png");
        title.setFilter(Image.FILTER_NEAREST);
        goBack = new Button("Main menu", Medrun.width / 2, Medrun.height - 90);
        sliders = new Slider[]{
            new Slider("Resolution", 3 * Medrun.width / 8, (4 * Medrun.height / 10) + HeightAjustment, 11, 5),
            new Slider("Framerate", 5 * Medrun.width / 8, (4 * Medrun.height / 10) + HeightAjustment, 5, 1),
            new Slider("Difficulty", 3 * Medrun.width / 8, (6 * Medrun.height / 10) + HeightAjustment, 4, 1),
            new Slider("Sound Master", 5 * Medrun.width / 8, (6 * Medrun.height / 10) + HeightAjustment, 0, 100),
            new Slider("Sound Effects", 3 * Medrun.width / 8, (8 * Medrun.height / 10) + HeightAjustment, 0, 100),
            new Slider("Music Volume", 5 * Medrun.width / 8, (8 * Medrun.height / 10) + HeightAjustment, 0, 100)
        };

        sliders[RESOLUTION].setValueText(String.valueOf(Medrun.getWidth()) + " x " + String.valueOf(Medrun.getHeight()));
        switch (Medrun.getHeight()) {
            case 360:
                sliders[RESOLUTION].setValue(0);
                break;
            case 720:
                sliders[RESOLUTION].setValue(1);
                break;
            case 800:
                sliders[RESOLUTION].setValue(2);
                break;
            case 768:
                sliders[RESOLUTION].setValue(3);
                break;
            case 900:
                sliders[RESOLUTION].setValue(4);
                break;
            case 1080:
                sliders[RESOLUTION].setValue(5);
                break;
            case 1152:
                sliders[RESOLUTION].setValue(6);
                break;
            case 1440:
                sliders[RESOLUTION].setValue(7);
                break;
            case 1620:
                sliders[RESOLUTION].setValue(8);
                break;
            case 1800:
                sliders[RESOLUTION].setValue(9);
                break;
            case 2160:
                sliders[RESOLUTION].setValue(10);
                break;
            default:
                sliders[RESOLUTION].setValue(0);
                sliders[RESOLUTION].setValueText("(Custom) " + String.valueOf(Medrun.getWidth()) + " x " + String.valueOf(Medrun.getHeight()));
                break;
        }

        sliders[FRAMERATE].setValueText(String.valueOf(Medrun.getTargetFramerate()) + "Hz");
        switch (Medrun.getTargetFramerate()) {
            case 30:
                sliders[FRAMERATE].setValue(0);
                break;
            case 60:
                sliders[FRAMERATE].setValue(1);
                break;
            case 90:
                sliders[FRAMERATE].setValue(2);
                break;
            case 120:
                sliders[FRAMERATE].setValue(3);
                break;
            case 144:
                sliders[FRAMERATE].setValue(4);
                break;
            default:
                sliders[FRAMERATE].setValue(0);
                sliders[FRAMERATE].setValueText("(custom) " + String.valueOf(Medrun.getTargetFramerate()) + "Hz");
                break;
        }

        switch ((int) Medrun.getDifficulty()) {
            case 1:
                sliders[DIFFICULTY].setValue(0);
                sliders[DIFFICULTY].setValueText("Easy");
                break;
            case 2:
                sliders[DIFFICULTY].setValue(1);
                sliders[DIFFICULTY].setValueText("Medium");
                break;
            case 3:
                sliders[DIFFICULTY].setValue(2);
                sliders[DIFFICULTY].setValueText("Hard");
                break;
            case 4:
                sliders[DIFFICULTY].setValue(3);
                sliders[DIFFICULTY].setValueText("Very Hard");
                break;
            default:
                sliders[DIFFICULTY].setValue(4);
                sliders[DIFFICULTY].setValueText("Custom (Good luck, you're gonna need it!)");
                break;
        }

        sliders[MASTERVOLUME].setValue(Medrun.getSoundMaster());
        sliders[MASTERVOLUME].setValueText("Master Volume: " + Medrun.getSoundMaster());
        sliders[EFFECTSVOLUME].setValue(Medrun.getSoundEffects());
        sliders[EFFECTSVOLUME].setValueText("Effects Volume: " + Medrun.getSoundEffects());
        sliders[MUSICVOLUME].setValue(Medrun.getSoundMusic());
        sliders[MUSICVOLUME].setValueText("Music Volume: " + Medrun.getSoundMusic());
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0, Medrun.getWidth(), Medrun.getHeight());
        Medrun.renderScaledCenter(title, Medrun.width / 2, 150, 1.5f);
        for (Slider slider : sliders) {
            slider.render();
        }
        goBack.render();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) { // If the player is holding the left mouse button down.
            if (!mouseCheck) {
                mouseCheck = true;
                int mouseX = input.getMouseX();
                int mouseY = input.getMouseY();
                for (int index = 0; index < sliders.length; index++) {
                    if (sliders[index].inSlider(mouseX, mouseY)) {
                        clickedSlider = index;
                    }
                }
            }
            if (clickedSlider != NONE) {
                sliders[clickedSlider].setSliderX(input.getMouseX());
                changeValueText(clickedSlider);
            } else {
                if (goBack.isInButton(input.getMouseX(), input.getMouseY())) {
                    if (!goBack.isClicked()) {
                        goBack.togglePress();
                    }
                } else if (goBack.isClicked()) {
                    goBack.togglePress();
                }
            }
        } else if (mouseCheck) { // If the mouse have been clicked but we haven't checked whats been clicked yet.
            mouseCheck = false;
            if (clickedSlider != NONE) {
                applyNewSetting(clickedSlider);
                clickedSlider = NONE;
            }
            if (goBack.clicked) {
                goBack.togglePress();
                sbg.enterState(Medrun.MENU);
            }
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(Medrun.MENU);
        }
    }

    private void changeValueText(int setting) {
        switch (setting) {
            case RESOLUTION:
                switch (sliders[clickedSlider].getValue()) {
                    case 0:
                        sliders[clickedSlider].setValueText("640 x 360");
                        break;
                    case 1:
                        sliders[clickedSlider].setValueText("1280 x 720");
                        break;
                    case 2:
                        sliders[clickedSlider].setValueText("1280 x 800");
                        break;
                    case 3:
                        sliders[clickedSlider].setValueText("1366 x 768");
                        break;
                    case 4:
                        sliders[clickedSlider].setValueText("1600 x 900");
                        break;
                    case 5:
                        sliders[clickedSlider].setValueText("1920 x 1080");
                        break;
                    case 6:
                        sliders[clickedSlider].setValueText("2048 x 1152");
                        break;
                    case 7:
                        sliders[clickedSlider].setValueText("2560 x 1440");
                        break;
                    case 8:
                        sliders[clickedSlider].setValueText("2880 x 1620");
                        break;
                    case 9:
                        sliders[clickedSlider].setValueText("3200 x 1800");
                        break;
                    case 10:
                        sliders[clickedSlider].setValueText("3840 x 2160");
                        break;
                }
                break;
            case FRAMERATE:
                switch (sliders[clickedSlider].getValue()) {
                    case 0:
                        sliders[clickedSlider].setValueText("30Hz");
                        break;
                    case 1:
                        sliders[clickedSlider].setValueText("60Hz");
                        break;
                    case 2:
                        sliders[clickedSlider].setValueText("90Hz");
                        break;
                    case 3:
                        sliders[clickedSlider].setValueText("120Hz");
                        break;
                    case 4:
                        sliders[clickedSlider].setValueText("144Hz");
                        break;
                }
                break;
            case DIFFICULTY:
                switch (sliders[clickedSlider].getValue()) {
                    case 0:
                        sliders[clickedSlider].setValueText("Easy");
                        break;
                    case 1:
                        sliders[clickedSlider].setValueText("Medium");
                        break;
                    case 2:
                        sliders[clickedSlider].setValueText("Hard");
                        break;
                    case 3:
                        sliders[clickedSlider].setValueText("Very Hard");
                        break;
                }
                break;
            case MASTERVOLUME:
                sliders[clickedSlider].setValueText("Master Volume: " + sliders[clickedSlider].getValue());
                break;
            case EFFECTSVOLUME:
                sliders[clickedSlider].setValueText("Effects Volume: " + sliders[clickedSlider].getValue());
                break;
            case MUSICVOLUME:
                sliders[clickedSlider].setValueText("Music Volume: " + sliders[clickedSlider].getValue());
                break;
        }
    }

    private void applyNewSetting(int setting) throws SlickException {
        switch (setting) {
            case RESOLUTION:
                switch (sliders[clickedSlider].getValue()) {
                    case 0:
                        Medrun.setGameResolution(640, 360);
                        break;
                    case 1:
                        Medrun.setGameResolution(1280, 720);
                        break;
                    case 2:
                        Medrun.setGameResolution(1280, 800);
                        break;
                    case 3:
                        Medrun.setGameResolution(1366, 768);
                        break;
                    case 4:
                        Medrun.setGameResolution(1600, 900);
                        break;
                    case 5:
                        Medrun.setGameResolution(1920, 1080);
                        break;
                    case 6:
                        Medrun.setGameResolution(2048, 1152);
                        break;
                    case 7:
                        Medrun.setGameResolution(2560, 1440);
                        break;
                    case 8:
                        Medrun.setGameResolution(2880, 1620);
                        break;
                    case 9:
                        Medrun.setGameResolution(3200, 1800);
                        break;
                    case 10:
                        Medrun.setGameResolution(3840, 2160);
                        break;
                }
                break;
            case FRAMERATE:
                switch (sliders[clickedSlider].getValue()) {
                    case 0:
                        Medrun.setTargetFramerate(30);
                        break;
                    case 1:
                        Medrun.setTargetFramerate(60);
                        break;
                    case 2:
                        Medrun.setTargetFramerate(90);
                        break;
                    case 3:
                        Medrun.setTargetFramerate(120);
                        break;
                    case 4:
                        Medrun.setTargetFramerate(144);
                        break;
                }
                break;
            case DIFFICULTY:
                Medrun.setDifficulty(sliders[clickedSlider].getValue());
                break;
            case MASTERVOLUME:
                Medrun.setSoundMaster(sliders[clickedSlider].getValue());
                break;
            case EFFECTSVOLUME:
                Medrun.setSoundEffects(sliders[clickedSlider].getValue());
                break;
            case MUSICVOLUME:
                Medrun.setSoundMusic(sliders[clickedSlider].getValue());
                break;
        }
    }

}
