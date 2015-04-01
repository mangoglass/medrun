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
 *
 * @author Admin
 */
class MenuState extends State {

    Input input;
    Button[] buttons;
    Image background;
    boolean mouseCheck;

    public MenuState(int stateID) {
        super(stateID);
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Entered Menu State");
    }

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Left Menu State");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mouseCheck = false;
        background = new Image("data/sprites/background.png");
        buttons = new Button[]{new Button("Start"), new Button("Leaderboards"), new Button("Achievements"),
            new Button("Settings"), new Button("Exit")};
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setY(Medrun.getHeight() / 5 + (75 * Button.scale * (i + 1)));
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0, Medrun.getWidth(), Medrun.getHeight());
        for (Button button : buttons) { // för alla button i arrayen buttons
            button.render(); // rendera button
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        input = gc.getInput();
        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            mouseCheck = true;
            for (Button button : buttons) {
                if (button.isInButton(input.getMouseX(), input.getMouseY())) {
                    if(!button.isClick()){
                        button.togglePress();
                    }
                } else if(button.isClick()){
                    button.togglePress();
                }
            }
        } else if (mouseCheck){
            mouseCheck = false;
            for (Button button : buttons) {
                if(button.isClick()){
                    button.togglePress();
                    switch (button.getTitle()){
                        case "Start": sbg.enterState(Medrun.GAME);
                        case "Leaderboards": 
                        case "Achievements":
                        case "Settings": 
                        case "Exit": gc.exit();
                    }
                }
            }
        }
    }
}
