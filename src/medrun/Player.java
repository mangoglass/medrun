/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Admin
 */
public class Player implements Renderable {

    public static final int X = 0;
    public static final int Y = 1;
    public static final float jumpHeight = 22;
    public static final float slowFall = 0.03f;
    public static final float quickFall = 0.06f;

    float x;
    float y;
    float xSpeed;
    float ySpeed;
    float xAcc;
    float yacc;
    float timeFlow;
    float jumpMultiplier;
    boolean onGround;
    boolean controlable;
    boolean sliding;
    boolean jumpButtonReleased;
    boolean slideButtonReleased;
    boolean dead;
    boolean foundCollision;
    int jumpPressTime;
    int slidePressTime;
    int slidePause;
    Animations animations;

    public Player(float x, float y) throws SlickException {
        this.x = x;
        this.y = y;
        animations = new Animations();
        jumpPressTime = 0;
        slidePressTime = 0;
        slidePause = 0;
        jumpButtonReleased = false;
        controlable = true;
        slideButtonReleased = false;
        dead = false;
        jumpMultiplier = 1;
        timeFlow = 1;
        xSpeed = GameState.dxChange;
    }

    public void update(int delta, Input input) {
        animations.update(delta, this);

        // Movement code starts here.
        // xSpeed = GameState.dxChange; // Add this functionality later!
        if (input.isKeyDown(Input.KEY_SPACE) && controlable && onGround) { // If player is on the ground and is controlable
            onGround = false;
            ySpeed = -jumpHeight * jumpMultiplier / timeFlow; // jump!
        } else if (input.isKeyDown(Input.KEY_SPACE) && controlable && !onGround && !jumpButtonReleased && jumpPressTime < 10000) { // if the player is in the air, is holding down the jump button, hasn't released the jumpbutton, hasn't held it for over three seconds, and is controllable.
            ySpeed += slowFall * delta / timeFlow; // fall slowly.
            jumpPressTime += delta / timeFlow; // update the amount of time the player has held down the jump button.
            System.out.println(jumpPressTime);
        } else if (!onGround && ((!input.isKeyDown(Input.KEY_SPACE) && !jumpButtonReleased) || jumpPressTime > 10000 || !controlable)) { // if the player is in the air, has released the jump button (unregistrered), or has held the jumpbutton for more than three seconds, or isn't controllable.
            jumpButtonReleased = true;
        } else if (jumpButtonReleased) { // if the player has released the jumpbutton, (or triggered any other event that makes this variable change to true).
            ySpeed += quickFall * delta / timeFlow; // fall quickly.
        }

        if (input.isKeyDown(Input.KEY_Z) && controlable && onGround && !sliding) { // If the player is holding the slide button down and is on the ground.
            sliding = true;
        } else if (input.isKeyDown(Input.KEY_Z) && !slideButtonReleased && controlable && onGround && sliding) {
            slidePressTime += delta;
        } else if (sliding && ((!input.isKeyDown(Input.KEY_Z) && !slideButtonReleased) || slidePressTime > 1500 || !controlable || !onGround)) {
            slideButtonReleased = true;
            sliding = false;
        }

        /*if (onGround && jumpButtonReleased) {
            jumpButtonReleased = false;
            ySpeed = 0;
            jumpPressTime = 0;
        }*/
        
        if (!sliding && slideButtonReleased && slidePause < 2000) {
            slidePause += delta;
        } else if (!sliding && slideButtonReleased && slidePause > 2000) {
            slideButtonReleased = false;
            slidePressTime = 0;
            slidePause = 0;
        }

        if (y > Medrun.height) { // if you have fallen out of the screen.
            if (!dead) {
                System.out.println("Out of bounds!  character is under  the lower limit that is:  " + Medrun.height + " pixels");
                dead = true;
            }
            if (GameState.dxChange > 0) {
                GameState.dxChange -= 0.2f;
            } else if (GameState.dxChange != 0) {
                GameState.dxChange = 0;
            }
        }

        float[] oldPos = {x, y}; // the characters position in the last frame.
        x += xSpeed * delta / 24; // update the x position for this frame! CHANGE THE VALUES LATER!
        y += ySpeed; // update the y position for this frame!

        // Collision code starts here
        
        if (!dead) {
            
            float[] vector = new float[]{x - oldPos[X], y - oldPos[Y]};
            float[] smallVector = vector.clone(); // smallVector is a clone of vector that will be smaller if the character has moved a large distance since the last drawn frame.
            while (Math.abs(smallVector[X]) > Block.tileWidth || Math.abs(smallVector[Y]) > Block.tileHeight) { // if our vector is bigger than the widht or height of one block
                smallVector[X] /= 2; // divide both x & y values in two.
                smallVector[Y] /= 2;
            }
            float[] partialVector = smallVector.clone();
            foundCollision = false;
            while (Math.abs(partialVector[X]) <= Math.abs(vector[X]) && !foundCollision) { // while the partial vector is smaller than the 'real' vector and we haven't found any collisions, we will check all significant positions for collision from earliest to newest position.

                float[] partialPos = {oldPos[X] + partialVector[X], oldPos[Y] + partialVector[Y]}; // the partial position is the actual position the player should be in.
                float[] topLeftPos = partialPos.clone(); // the topLeft position is the same at the partial one, I use both to not create any confusions regarding the names.
                float[] topRightPos = {oldPos[X] + partialVector[X] + Animations.width, oldPos[Y] + partialVector[Y]};
                float[] botRightPos = {oldPos[X] + partialVector[X] + Animations.width, oldPos[Y] + partialVector[Y] + Animations.height};
                float[] botLeftPos = {oldPos[X] + partialVector[X], oldPos[Y] + partialVector[Y] + Animations.height};
                for (Block block : GameState.getActiveBlocks()) { // for each block in the active block list.
                    if (block.inBlock(topLeftPos) || block.inBlock(topRightPos) || block.inBlock(botRightPos) || block.inBlock(botLeftPos)) { // If the character is in the block.

                        boolean topLeftcol = block.isColliding(topLeftPos);
                        boolean topRightcol = block.isColliding(topRightPos);
                        boolean botRightcol = block.isColliding(botRightPos);
                        boolean botLeftcol = block.isColliding(botLeftPos);

                        if (topLeftcol || topRightcol || botRightcol || botLeftcol) { // if the character colides with any colidible block in this position.
                            foundCollision = true; // we have found a collision! 
                            if (topLeftcol && !botLeftcol) {
                                if (topRightcol && !botRightcol) { // if the top left and right points have colided with a block / multiple blocks.
                                    if (onGround) { // if we are on the ground.
                                        die(partialPos); // you die, the player has probably hit a ceiling because said player stopped sliding while under an low obstacle.
                                    } else { // if we are in air.
                                        ySpeed = 0;
                                        jumpButtonReleased = true;
                                        setX(partialPos[X]); // change x position to the position of the partial vector.
                                        setY(block.getBlockPos(topRightPos)[Y] + block.tiledMap.getTileHeight() + 1); // change y position to the position of the bottom of the block that we colided with.
                                    }
                                } else if (botRightcol && !topRightcol) { // if the top left and bottom right points have colided with a block / multiple blocks.
                                    die(partialPos);
                                } else if (botRightcol && topRightcol) { // if the top left, right and bottom right points have colided with a block / multiple blocks.
                                    die(partialPos);
                                }
                            } else if (botLeftcol && !topLeftcol) {
                                if (topRightcol && !botRightcol) {
                                    die(partialPos);
                                } else if (botRightcol && !topRightcol) {
                                    setOnGround();
                                    setX(partialPos[X]);
                                    setY(block.getBlockPos(botRightPos)[Y] - Animations.height - 1);
                                } else if (botRightcol && topRightcol) {
                                    die(partialPos);
                                } else {
                                    //if(partialPos[X])
                                }
                            } else if (topLeftcol && botLeftcol) {
                                if (topRightcol && !botRightcol) {
                                    ySpeed = 0;
                                    jumpButtonReleased = true;
                                    setX(partialPos[X]);
                                    setY(block.getBlockPos(topRightPos)[Y] + block.tiledMap.getTileHeight() + 1); // change y position to the position of the bottom of the block that we colided with.
                                } else if (botRightcol && !topRightcol) {
                                    setOnGround();
                                    setX(partialPos[X]);
                                    setY(block.getBlockPos(botRightPos)[Y] - Animations.height - 1);
                                } else if (botRightcol && topRightcol) {
                                    die(partialPos);
                                }
                            } else {
                                if (topRightcol && !botRightcol) {
                                    float[] blocky = block.getBlockPos(topRightPos);
                                    if (topRightPos[Y] - blocky[Y] >= 2 * block.getTiledHeight() / 3) { // If the distance between the character and the block is greater than two thirds, the character get's to live.
                                        setX(partialPos[X]);
                                        setY(blocky[Y] + block.tiledMap.getTileHeight() + 1);
                                    } else {
                                        die(partialPos);
                                    }
                                } else if (botRightcol && !topRightcol) {
                                    float[] blocky = block.getBlockPos(botRightPos);
                                    if (botRightPos[Y] - blocky[Y] <= block.getTiledHeight() / 3) {
                                        setX(partialPos[X]);
                                        setY(blocky[Y] + block.tiledMap.getTileHeight() + 1);
                                    } else {
                                        die(partialPos);
                                    }
                                } else if (botRightcol && topRightcol) {
                                    die(partialPos);
                                }
                            }
                        }
                        break; // break out of the position checking, since the character can only be in one block.
                    }
                }
                partialVector[X] += smallVector[X];
                partialVector[Y] += smallVector[Y];
            }
            //break; // break out of the block checking if the character is in a block (the for loop). Unneccecary to check others as the character can only be in one at a time.

        }
    }

    @Override
    public void render() {
        animations.draw(x, y);
    }

    public void die(float[] pos) {
        setX(pos[X]);
        setY(pos[Y]);
        controlable = false;
        jumpButtonReleased = true;
        slideButtonReleased = true;
        onGround = false;
        dead = true;
    }
    
    public void setOnGround(){
        onGround = true;
        jumpButtonReleased = false;
        ySpeed = 0;
        jumpPressTime = 0;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public float getxAcc() {
        return xAcc;
    }

    public void setxAcc(float xAcc) {
        this.xAcc = xAcc;
    }

    public float getYacc() {
        return yacc;
    }

    public void setYacc(float yacc) {
        this.yacc = yacc;
    }

    public float getTimeFlow() {
        return timeFlow;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean isSliding() {
        return sliding;
    }
}
