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

    float x;
    float y;
    float[] topLeftPos;
    float[] topRightPos;
    float[] botRightPos;
    float[] botLeftPos;
    float xSpeed;
    float ySpeed;
    float xAcc;
    float yacc;
    float timeFlow;
    float jumpHeight;
    boolean onGround;
    boolean controlable;
    boolean rightColide;
    boolean bottomColide;
    boolean topColide;
    boolean sliding;
    boolean falling;
    boolean slideButtonReleased;
    boolean dead;
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
        falling = false;
        controlable = true;
        slideButtonReleased = false;
        dead = false;
        jumpHeight = 1;
        timeFlow = 1;
        xSpeed = GameState.dxChange;

        topLeftPos = new float[]{x, y};
        topRightPos = new float[]{x + Animations.width, y};
        botRightPos = new float[]{x + Animations.width, y + Animations.height};
        botLeftPos = new float[]{x, y + Animations.height};
    }

    public void update(int delta, Input input) {
        animations.update(delta);

        // Movement code starts here.
        if (input.isKeyDown(Input.KEY_SPACE) && controlable && onGround) {
            onGround = false;
            ySpeed = -10 * jumpHeight / timeFlow;
        } else if (input.isKeyDown(Input.KEY_SPACE) && controlable && !onGround && !falling && jumpPressTime < 3000) {
            ySpeed += 0.01f * delta / timeFlow;
            jumpPressTime += delta / timeFlow;
        } else if (!onGround && ((!input.isKeyDown(Input.KEY_SPACE) && !falling) || jumpPressTime > 3000 || !controlable)) {
            falling = true;
        } else if (falling) {
            ySpeed += 0.03f * delta / timeFlow;
        }

        if (input.isKeyDown(Input.KEY_Z) && controlable && onGround && !sliding) {
            sliding = true;
        } else if (input.isKeyDown(Input.KEY_Z) && !slideButtonReleased && controlable && onGround && sliding) {
            slidePressTime += delta;
        } else if (sliding && ((!input.isKeyDown(Input.KEY_Z) && !slideButtonReleased) || slidePressTime > 1500 || !controlable || !onGround)) {
            slideButtonReleased = true;
            sliding = false;
        }

        if (onGround && falling) {
            falling = false;
            ySpeed = 0;
            jumpPressTime = 0;
        }
        if (!sliding && slideButtonReleased && slidePause < 2000) {
            slidePause += delta;
        } else if (!sliding && slideButtonReleased && slidePause > 2000) {
            slideButtonReleased = false;
            slidePressTime = 0;
            slidePause = 0;
        }

        if (y > Medrun.gameHeight) {
            GameState.dxChange = 0;
        }

        x += xSpeed * delta/24; // CHANGE THIS LATER!
        y += ySpeed;

        // Collision code starts here.
        if (!dead) {
            float[] oldPos = {topLeftPos[X], topLeftPos[Y]}; // the characters position in the last frame.
            topLeftPos = new float[]{x, y};
            topRightPos = new float[]{x + Animations.width, y};
            botRightPos = new float[]{x + Animations.width, y + Animations.height};
            botLeftPos = new float[]{x, y + Animations.height};
             float[] vector = new float[]{oldPos[X] - topLeftPos[X], oldPos[Y] - topLeftPos[Y]};

            for (Block block : GameState.getActiveBlocks()) { // for each block in the active block list.
                if (block.inBlock(topLeftPos) || block.inBlock(topRightPos) || block.inBlock(botRightPos) || block.inBlock(botLeftPos)) { // If the character is in the block.
                    float[] smallVector = vector.clone(); // smallVector is a clone of vector that will be smaller if the character has moved a large distance since the last drawn frame.
                    while (smallVector[X] > block.getWidth() || smallVector[Y] > block.getHeight()) { // if our vector is bigger than the widht or height of one block
                        smallVector[X] /= 2; // divide both x & y values in two.
                        smallVector[Y] /= 2;
                    }
                    float[] partialVector = smallVector.clone();
                    while (partialVector[X] <= vector[X]) { // while the partial vector is smaller than the 'real' vector, we will check all significant positions for collision from earliest to newest position.

                        boolean topLeftcol = block.isColliding(topLeftPos);
                        boolean topRightcol = block.isColliding(topRightPos);
                        boolean botRightcol = block.isColliding(botRightPos);
                        boolean botLeftcol = block.isColliding(botLeftPos);

                        if (topLeftcol || topRightcol || botRightcol || botLeftcol) {
                            if (topLeftcol && !botLeftcol) {
                                if (topRightcol && !botRightcol) { // if the top points have colided with blocks.
                                    if (onGround) {
                                        die(partialVector);
                                    } else { // if we are in air.
                                        ySpeed = 0;
                                        falling = true;
                                        setX(partialVector[X]);
                                        setY(block.getBlockPos(topRightPos)[Y] + block.tiledMap.getTileHeight() + 1); // change y position to the position of the bottom of the block that we colided with.
                                    }
                                } else if (botRightcol && !topRightcol) {
                                    die(partialVector);
                                } else if (botRightcol && topRightcol) {
                                    die(partialVector);
                                } 
                            } else if (botLeftcol && !topLeftcol) {
                                if (topRightcol && !botRightcol) {
                                    die(partialVector);
                                } else if (botRightcol && !topRightcol) {
                                    onGround = true;
                                    setX(partialVector[X]);
                                    setY(block.getBlockPos(botRightPos)[Y] - Animations.height - 1);
                                } else if (botRightcol && topRightcol) {
                                    die(partialVector);
                                } 
                            } else if (topLeftcol && botLeftcol) {
                                if (topRightcol && !botRightcol) {
                                    ySpeed = 0;
                                    falling = true;
                                    setX(partialVector[X]);
                                    setY(block.getBlockPos(topRightPos)[Y] + block.tiledMap.getTileHeight() + 1); // change y position to the position of the bottom of the block that we colided with.
                                } else if (botRightcol && !topRightcol) {
                                    onGround = true;
                                    setX(partialVector[X]);
                                    setY(block.getBlockPos(botRightPos)[Y] - Animations.height - 1);
                                } else if (botRightcol && topRightcol) {
                                    die(partialVector);
                                } 
                            } else {
                                if (topRightcol && !botRightcol) {
                                    float[] blocky = block.getBlockPos(topRightPos);
                                    if(topRightPos[Y] - blocky[Y] >= 2*block.getTiledHeight()/3){ // If the distance between the character and the block is greater than two thirds, the character get's to live.
                                        setX(partialVector[X]);
                                        setY(blocky[Y] + block.tiledMap.getTileHeight() + 1);
                                    } else{
                                        die(partialVector);
                                    }
                                } else if (botRightcol && !topRightcol) {
                                    float[] blocky = block.getBlockPos(botRightPos);
                                    if(botRightPos[Y] - blocky[Y] <= block.getTiledHeight()/3){
                                        setX(partialVector[X]);
                                        setY(blocky[Y] + block.tiledMap.getTileHeight() + 1);
                                    } else{
                                        die(partialVector);
                                    }
                                } else if (botRightcol && topRightcol) {
                                    die(partialVector);
                                } 
                            }
                            break; // break out of the position checking, since we already found one collision at the earliest possible position.
                        }

                        partialVector[X] += smallVector[X];
                        partialVector[Y] += smallVector[Y];
                    }
                    break; // break out of the block checking if the character is in a block (the for loop). Unneccecary to check others as the character can only be in one at a time.
                }
            }
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
        falling = true;
        slideButtonReleased = true;
        onGround = false;
        dead = true;
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

}
