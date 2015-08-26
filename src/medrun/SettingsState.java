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

    private Image background;
    private boolean mouseCheck;
    private boolean sliderCheck;
    private Image title;
    private final float fontSize = 150;
    private Button goBack;
    private Slider[] sliders;
    int mouseX;
    int mouseY;
    Input input;

    public SettingsState(int stateID) {
        super(stateID);
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
        sliderCheck = false;
        background = new Image("data/sprites/layers/layer0.png");
        title = new Image("data/sprites/settings.png");
        title.setFilter(Image.FILTER_NEAREST);
        goBack = new Button("Main menu", Medrun.width / 2, Medrun.height - 30);
        sliders = new Slider[]{new Slider("res", Medrun.width/3, 3*Medrun.height/6), new Slider("fra", 2*Medrun.width/3, 3*Medrun.height/6), 
            new Slider("dif", Medrun.width/3, 4*Medrun.height/6), new Slider("mas", 2*Medrun.width/3, 4*Medrun.height/6),
            new Slider("eff", Medrun.width/3, 5*Medrun.height/6), new Slider("mus", 2*Medrun.width/3, 5*Medrun.height/6)};
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0, Medrun.getWidth(), Medrun.getHeight());
        Medrun.renderScaledCenter(title, Medrun.width / 2, 200, 1.2f);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        input = gc.getInput();
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) { // If the player is holding the left mouse button down.
            if(sliderCheck == false){
                sliderCheck = true;
                mouseX = input.getMouseX();
                mouseY = input.getMouseY();
            }
            mouseCheck = true;
            if (goBack.isInButton(input.getMouseX(), input.getMouseY())) {
                if (!goBack.isClicked()) {
                    goBack.togglePress();
                }
            } else if (goBack.isClicked()) {
                goBack.togglePress();
            }
            
        } else if (mouseCheck) { // If the mouse have been clicked but we haven't checked whats been clicked yet.
            mouseCheck = false;
            sliderCheck = false;
            if(goBack.clicked){
                sbg.enterState(Medrun.MENU);
            } 
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            sbg.enterState(Medrun.MENU);
        }
    }

}
