/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Input;

/**
 *
 * @author Admin
 */
public class Player implements Renderable {

    float x;
    float y;
    float xSpeed;
    float ySpeed;
    float xAcc;
    float yacc;
    float timeFlow;
    boolean onGround;
    boolean controlable;
    boolean rightColide;
    boolean bottomColide;
    boolean topColide;
    boolean slideing;
    boolean jumpButtonReleased;
    int jumpPressTime;
    int jumpHeight;
    PlayerAnimations animations;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        animations = new PlayerAnimations();
        jumpPressTime = 0;
        jumpButtonReleased = false;
        jumpHeight = 1;
        timeFlow = 1;
    }

    public void update(int delta, Input input) {
        animations.update(delta);

        if (input.isKeyDown(Input.KEY_SPACE) && controlable && onGround) {
            onGround = false;
            y = -10/timeFlow;
        } else if (input.isKeyDown(Input.KEY_SPACE) && controlable && !onGround && !jumpButtonReleased && jumpPressTime < 3000) {
            y += 0.01f*delta/timeFlow;
            jumpPressTime += delta/timeFlow;
        } else if(!onGround && ((!input.isKeyDown(Input.KEY_SPACE) && !jumpButtonReleased) || jumpPressTime > 3000 || !controlable)){
            jumpButtonReleased = true;
            y += 0.03f*delta/timeFlow;
        }
        
        if (input.isKeyDown(Input.KEY_Z) && controlable && onGround) {
            
        }

        if (onGround && jumpButtonReleased) {
            jumpButtonReleased = false;
        }
    }

    @Override
    public void render() {
        animations.draw(x, y);
    }
}
