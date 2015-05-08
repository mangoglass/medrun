/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The Player Class creates the player object which the person playing control's
 * with chosen peripheral.
 *
 * @author Admin
 */
public class Player implements Renderable {

    public static final int X = 0;
    public static final int Y = 1;
    public static final float speedDiviator = 1.5f;
    public static final float jumpHeight = 15;
    public static final float slowFall = 0.3f;
    public static final float quickFall = 0.8f;
    public static final float maxFallSpeed = 30;
    public static final int maxIndentX = 2 * Block.tileWidth / 3;
    public static final int maxIndentY = 2 * Block.tileHeight / 3;
    public static final int xMargin = (int) Animations.width / 3;
    public static final int yMargin = (int) Animations.height / 3;

    float x;
    float y;
    float xSpeed;
    float ySpeed;
    float xAcc;
    float yacc;
    float jumpMultiplier;
    float speedDif;
    float deathScale; // how much the player will scale in size when dead.
    boolean onGround;
    boolean controlable;
    boolean forwards;
    boolean backwards;
    boolean sliding;
    boolean jumpButtonReleased;
    boolean slideButtonReleased;
    boolean dead;
    boolean foundCollision;
    boolean foundOverBlock;
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
        onGround = false;
        jumpButtonReleased = false;
        controlable = true;
        slideButtonReleased = false;
        dead = false;
        foundOverBlock = false;
        jumpMultiplier = 1;
        speedDif = 0;
    }

    public void update(int delta, float deltaRatio, Input input) {
        xSpeed = GameState.dTranslatedX + speedDif;
        animations.update(delta, this);

        // Movement code starts here.
        if (input.isKeyDown(Input.KEY_SPACE) && controlable && onGround) { // If player is on the ground and is controlable
            onGround = false;
            if(sliding){
                sliding = false;
            }
            ySpeed = -jumpHeight * jumpMultiplier / GameState.timeFlow; // jump!
        } else if (input.isKeyDown(Input.KEY_SPACE) && controlable && !onGround && !jumpButtonReleased && ySpeed < 0) { // if the player is in the air, is holding down the jump button, hasn't released the jumpbutton, hasn't held it for over three seconds, and is controllable.       
            ySpeed += slowFall * deltaRatio / GameState.timeFlow; // fall slowly.
            jumpPressTime += deltaRatio / GameState.timeFlow; // update the amount of time the player has held down the jump button.
        } else if (!onGround && !jumpButtonReleased && (!input.isKeyDown(Input.KEY_SPACE) || !controlable || ySpeed >= 0)) { // if the player is in the air, has released the jump button (unregistrered), or has held the jumpbutton for more than three seconds, or isn't controllable.
            jumpButtonReleased = true;
        } else if (jumpButtonReleased) { // if the player has released the jumpbutton, (or triggered any other event that makes this variable change to true).
            if (ySpeed < maxFallSpeed) {
                ySpeed += quickFall * deltaRatio / GameState.timeFlow; // fall quickly.
            } else {
                ySpeed = maxFallSpeed;
            }
        }

        if (input.isKeyDown(Input.KEY_S) && controlable && onGround && !sliding) { // If the player is holding the slide button down and is on the ground.
            sliding = true;
        } else if (input.isKeyDown(Input.KEY_S) && !slideButtonReleased && controlable && onGround && sliding) {
            slidePressTime += delta;
        } else if (sliding && ((!input.isKeyDown(Input.KEY_S) && !slideButtonReleased) || slidePressTime > 2000 || !controlable || !onGround)) {
            slideButtonReleased = true;
            sliding = false;
        }

        if (input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A) && controlable) {
            if (!forwards) {
                forwards = true;
                backwards = false;
            } else {
                if (onGround) {
                    speedDif = GameState.dTranslatedX / speedDiviator;
                } else {
                    speedDif = GameState.dTranslatedX / (speedDiviator + 1);
                }
            }
        } else if (input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D) && controlable) {
            if (!backwards) {
                backwards = true;
                forwards = false;
            } else {
                if (onGround) {
                    speedDif = -GameState.dTranslatedX / speedDiviator;
                } else {
                    speedDif = -GameState.dTranslatedX / (speedDiviator + 1);
                }
            }
        } else if (forwards || backwards) {
            speedDif = 0;
            forwards = false;
            backwards = false;
        }

        if (!sliding && slideButtonReleased && slidePause < 1000) {
            slidePause += delta;
        } else if (!sliding && slideButtonReleased && slidePause >= 1000) {
            slideButtonReleased = false;
            slidePressTime = 0;
            slidePause = 0;
        }

        if (y > Medrun.height) { // if you have fallen out of the screen.
            if (!dead) {
                System.out.println("Out of bounds!  character is under  the lower limit that is:  " + Medrun.height + " pixels");
                dead = true;
            }
        }

        float[] oldPos = {x, y}; // the characters position in the last frame.
        x += xSpeed * deltaRatio; // update the x position for this frame!
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
            foundOverBlock = false;
            while (Math.abs(partialVector[X]) <= Math.abs(vector[X]) && !foundCollision) { // while the partial vector is smaller than the 'real' vector and we haven't found any collisions, we will check all significant positions for collision from earliest to newest position.
                float[] partialPos = {oldPos[X] + partialVector[X], oldPos[Y] + partialVector[Y]}; // the partial position is the actual position the player should be in.
                float[] topLeftPos = {oldPos[X] + partialVector[X] + xMargin, oldPos[Y] + partialVector[Y] + yMargin}; // the topLeft position is the same at the partial one, I use both to not create any confusions regarding the names.
                float[] topRightPos = {oldPos[X] + partialVector[X] + Animations.width - xMargin, oldPos[Y] + partialVector[Y] + yMargin};
                float[] botRightPos = {oldPos[X] + partialVector[X] + Animations.width - xMargin, oldPos[Y] + partialVector[Y] + Animations.height};
                float[] botLeftPos = {oldPos[X] + partialVector[X] + xMargin, oldPos[Y] + partialVector[Y] + Animations.height};

                for (Block block : GameState.getActiveBlocks()) { // for each block in the active block list.

                    groundCheck(block, botLeftPos, botRightPos, GameState.getActiveBlocks().size(), GameState.getActiveBlocks().indexOf(block) + 1); // Checks if the player is running in the air. And if so, makes the player fall.
                    if (block.inBlock(topLeftPos) || block.inBlock(topRightPos) || block.inBlock(botRightPos) || block.inBlock(botLeftPos)) { // If the character is in the block.

                        boolean topLeftcol = block.isColliding(topLeftPos);
                        boolean topRightcol = block.isColliding(topRightPos);
                        boolean botRightcol = block.isColliding(botRightPos);
                        boolean botLeftcol = block.isColliding(botLeftPos);

                        if (topLeftcol || topRightcol || botRightcol || botLeftcol) { // if the character colides with any colidible block in this position.

                            foundCollision = true; // we have found a collision! 

                            //Single corner collisions
                            if (topLeftcol && !topRightcol && !botRightcol && !botLeftcol) {
                                if ((onGround && !sliding) || !block.isBottomTile(topLeftPos)) {
                                    die(partialPos, onGround);
                                } else if (ySpeed < 0) {
                                    setBelowRoof(partialPos, block);
                                }
                            } else if (!topLeftcol && topRightcol && !botRightcol && !botLeftcol) {
                                if ((onGround && !sliding) || !block.isBottomTile(topRightPos)) {
                                    die(partialPos, onGround);
                                } else if (ySpeed < 0) {
                                    setBelowRoof(topLeftPos, block);
                                }
                            } else if (!topLeftcol && !topRightcol && botRightcol && !botLeftcol) {
                                if (ySpeed < 0) {
                                    die(partialPos, onGround);
                                } else {
                                    setOnGround(botRightPos, block, false);
                                }
                            } else if (!topLeftcol && !topRightcol && !botRightcol && botLeftcol) {
                                if (ySpeed >= 0) {
                                    setOnGround(botRightPos, block, false);
                                }
                            } //Double corner collisions
                            else if (topLeftcol && topRightcol && !botRightcol && !botLeftcol) {
                                if ((onGround && !sliding) || !block.isBottomTile(topLeftPos)) { // if we are on the ground.
                                    die(partialPos, onGround); // you die, the player has probably hit a ceiling because said player stopped sliding while under an low obstacle.
                                } else if (ySpeed < 0) { // if we are in air. And are moving up
                                    setBelowRoof(topLeftPos, block);
                                }
                            } else if (topLeftcol && !topRightcol && botRightcol && !botLeftcol) {
                                die(partialPos, onGround);
                            } else if (topLeftcol && !topRightcol && !botRightcol && botLeftcol) {
                                // Nothing.
                            } else if (!topLeftcol && topRightcol && botRightcol && !botLeftcol) {
                                die(partialPos, onGround); // ran in to a wall.
                            } else if (!topLeftcol && topRightcol && !botRightcol && botLeftcol) {
                                die(partialPos, onGround);
                            } else if (!topLeftcol && !topRightcol && botRightcol && botLeftcol) {
                                if (ySpeed >= 0) {
                                    setOnGround(botLeftPos, block, true);
                                } else {
                                    die(partialPos, onGround);
                                }
                            } //Tripple corner collisions
                            else if (topLeftcol && topRightcol && botRightcol && !botLeftcol) {
                                die(partialPos, onGround);
                            } else if (topLeftcol && topRightcol && !botRightcol && botLeftcol) {
                                setBelowRoof(topLeftPos, block);
                            } else if (topLeftcol && !topRightcol && botRightcol && botLeftcol) {
                                setOnGround(botLeftPos, block, true);
                            } else if (!topLeftcol && topRightcol && botRightcol && botLeftcol) {
                                die(partialPos, onGround);
                            } //Four corner collision
                            else {
                                die(partialPos, onGround);
                            }
                        }
                        break; // break out of the position checking, since the character can only be in one block.
                    }
                }
                partialVector[X] += smallVector[X];
                partialVector[Y] += smallVector[Y];

                if (vector[X] == 0) { // stops an infinite loop caused by the character not moving and the game trying recheck the current position.
                    break;
                }
            }
            
        }

        if (dead) { // If the player is dead
            if (!onGround) {
                Animations.width *= deathScale;
                Animations.height *= deathScale;
            } else {
                xSpeed *= 0.001f; //xSpeed is negative when you're dead, so this will lower the negative speed untill it almost reaches zero.
            } 
        }
    }

    @Override
    public void render() {
        animations.draw(x, y);
    }

    public void die(float[] pos, boolean onGround) {
        setX(pos[X]);
        setY(pos[Y]);
        xSpeed = (float) (-xSpeed / (1.5 + Math.random() / 2)); // the  negative xSpeed divided by a numer ranging between 1.5 and 2.
        controlable = false;
        slideButtonReleased = true;
        dead = true;
        if (!onGround) {
            jumpButtonReleased = true;
            deathScale = 1 + (float) Math.random() / 25;
        } else {
            deathScale = 1;
        }
    }

    public void setOnGround() {
        onGround = true;
        jumpButtonReleased = false;
        ySpeed = 0;
        jumpPressTime = 0;
    }

    public void setOnGround(float[] pos, Block block, boolean leftPos) {
        if (leftPos) {
            setX(pos[X] - xMargin);
        } else {
            setX(pos[X] + xMargin - Animations.width);
        }

        setY(block.getTilePixelPos(pos)[Y] - Animations.height - 1);
        onGround = true;
        jumpButtonReleased = false;
        ySpeed = 0;
        jumpPressTime = 0;
    }

    public void setBelowRoof(float[] pos, Block block) {
        setX(pos[X] - xMargin); // change x position to the position of the partial vector.
        setY(block.getTilePixelPos(pos)[Y] + block.tiledMap.getTileHeight() + 1); // change y position to the position of the bottom of the block that we colided with.
        ySpeed = 0;
        jumpButtonReleased = true;
    }

    /**
     * Changes the players onGround variable to false (meaning the player is
     * falling) if the blocks beneath the feet of the player isn't collidable.
     *
     * @param block the block that we should check in relation to the players
     * position.
     * @param botLeft the bottom left position of the player
     * @param botRight the bottom right position of the player
     * @param lastBlockIndex
     * @param index
     */
    public void groundCheck(Block block, float[] botLeft, float[] botRight, int lastBlockIndex, int index) {
        if (onGround && (block.inXRangeOfBlock(botLeft) || block.inXRangeOfBlock(botRight))) { 
            foundOverBlock = true;
            if (!block.isColliding(new float[]{botLeft[X], botLeft[Y] + block.getTiledHeight() / 2})
                    && !block.isColliding(new float[]{botRight[X], botRight[Y] + block.getTiledHeight() / 2})) {
                this.onGround = false;
            }
        } else if(onGround && index == lastBlockIndex){
            this.onGround = false;
        }
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

    public boolean isOnGround() {
        return onGround;
    }

    public boolean isSliding() {
        return sliding;
    }
}
