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
 * @author Tom Axblad
 */
public class Player implements Renderable {

    public static final int X = 0;
    public static final int Y = 1;
    public static final float SPEEDDIVIATOR = 1.5f;
    public static final float JUMPHEIGHT = 18;
    public static final float SLOWFALL = 0.5f;
    public static final float QUICKFALL = 1.1f;
    public static final float MAXFALLSPEED = 30;
    public static final int SCREENMARGIN = 200;
    public static final int SLIDEMAXPAUSE = 500;
    public static final int MAXINDENTX = 2 * Block.TILEWIDTH / 3;
    public static final int MAXINDENTY = 2 * Block.TILEHEIGHT / 3;
    public static final int XMARGIN = (int) Animations.width / 3;
    public static final int YMARGIN = (int) Animations.height / 3;
    public static final int MAXJUMPHEIGHT = getMaxJumpHeight();

    float x;
    float y;
    float xSpeed;
    float ySpeed;
    float xAcc;
    float yAcc;
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
    int slidePressTime;
    int slidePause;
    Animations animations;

    /**
     * The player constructor that defines all the values for the player object.
     *
     * @param x What x-value the player will start at.
     * @param y What y-value the player will start at.
     * @throws SlickException
     */
    public Player(float x, float y) throws SlickException {
        this.x = x;
        this.y = y;
        animations = new Animations();
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

    /**
     * The player test constructor that is used to test the player class.
     *
     * @param x What x-value the player will start at.
     * @param y What y-value the player will start at.
     * @param test separates this constructor from the previous.
     */
    public Player(float x, float y, boolean test) {
        this.x = x;
        this.y = y;
        xSpeed = 0;
        ySpeed = 0;
        xAcc = 0;
        yAcc = 0;
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

    /**
     * The update method for the player. This updater will run once every frame.
     *
     * @param delta The time between this frame and the last measured in
     * milliseconds.
     * @param deltaRatio The amount the last delta differences from the usual
     * delta size.
     * @param input The users keyboard input from the last frame
     */
    public void update(int delta, float deltaRatio, Input input) {
        xSpeed = GameState.dTranslatedX + speedDif;
        animations.update(delta, this);

        // Movement code starts here.
        if (input.isKeyDown(Input.KEY_SPACE) && controlable && onGround) { // If player is on the ground and is controlable
            onGround = false;
            if (sliding) {
                sliding = false;
            }
            ySpeed = -JUMPHEIGHT * jumpMultiplier / GameState.timeFlow; // jump!
        } else if (input.isKeyDown(Input.KEY_SPACE) && controlable && !onGround && !jumpButtonReleased && ySpeed < 0) { // if the player is in the air, is holding down the jump button, hasn't released the jumpbutton, hasn't held it for over three seconds, and is controllable.       
            ySpeed += SLOWFALL * deltaRatio / GameState.timeFlow; // fall slowly.
        } else if (!onGround && !jumpButtonReleased && (!input.isKeyDown(Input.KEY_SPACE) || !controlable || ySpeed >= 0)) { // if the player is in the air, has released the jump button (unregistrered), or has held the jumpbutton for more than three seconds, or isn't controllable.
            jumpButtonReleased = true;
        } else if (jumpButtonReleased) { // if the player has released the jumpbutton, (or triggered any other event that makes this variable change to true).
            if (ySpeed < MAXFALLSPEED) {
                ySpeed += QUICKFALL * deltaRatio / GameState.timeFlow; // fall quickly.
            } else {
                ySpeed = MAXFALLSPEED;
            }
        }

        if (input.isKeyDown(Input.KEY_S) && controlable && onGround && !sliding && !slideButtonReleased) { // If the player is holding the slide button down and is on the ground.
            sliding = true;
        } else if (input.isKeyDown(Input.KEY_S) && !slideButtonReleased && controlable && onGround && sliding && slidePressTime < 1500) {
            slidePressTime += delta;
            System.out.println(slidePressTime);
        } else if (sliding && ((!input.isKeyDown(Input.KEY_S) && !slideButtonReleased) || slidePressTime > 1500 || !controlable || !onGround)) {
            slideButtonReleased = true;
            sliding = false;
        }

        if (input.isKeyDown(Input.KEY_D) && !input.isKeyDown(Input.KEY_A) && controlable && x + Animations.width < GameState.translatedX + Medrun.width - SCREENMARGIN) {
            // if the player wants to move to the right and the player can.
            if (!forwards) {
                forwards = true;
                backwards = false;
            } else {
                if (onGround && !sliding) {
                    speedDif = GameState.dTranslatedX / SPEEDDIVIATOR; // Changes the speedDif variable to the currentAnimation moving speed divided by the speedDiviator.
                } else { // if the player is sliding or is in the air.
                    speedDif = GameState.dTranslatedX / (SPEEDDIVIATOR + 1.5f); // Changes the speedDif variable to the currentAnimation moving speed divided by (speedDiviator + 1.5).
                }
            }
        } else if (input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D) && controlable && x > GameState.translatedX + SCREENMARGIN) {
            if (!backwards) {
                backwards = true;
                forwards = false;
            } else {
                if (onGround && !sliding) {
                    speedDif = -GameState.dTranslatedX / SPEEDDIVIATOR; // Changes the speedDif variable to the negative currentAnimation moving speed divided by the speedDiviator.
                } else {
                    speedDif = -GameState.dTranslatedX / (SPEEDDIVIATOR + 1.5f); // Changes the speedDif variable to the negative currentAnimation moving speed divided by (speedDiviator + 1.5).
                }
            }
        } else if (forwards || backwards) {
            speedDif = 0; // changes the speed to the normal speed.
            forwards = false;
            backwards = false;
        }

        if (!sliding && slideButtonReleased && slidePause < SLIDEMAXPAUSE) {
            slidePause += delta;
        } else if (!sliding && slideButtonReleased && slidePause >= SLIDEMAXPAUSE) {
            slideButtonReleased = false;
            slidePressTime = 0;
            slidePause = 0;
        }

        if (y > GameState.translatedY + Medrun.height) { // if you have fallen out of the screen.
            if (!dead) {
                System.out.println("Out of bounds!  character is under  the lower limit that is:  " + (int) (GameState.translatedY + Medrun.height) + " pixels");
                dead = true;
            }
        }

        float[] oldPos = {x, y}; // the characters position in the last frame.
        x += xSpeed * deltaRatio; // update the x position for this frame!
        y += ySpeed; // update the y position for this frame!

        // Collision code starts here
        if (!dead) {
            float[] vector = new float[]{x - oldPos[X], y - oldPos[Y]}; // the vector variable is the difference in x and y-axis between the last position and the new one.
            float[] smallVector = vector.clone(); // smallVector is a clone of vector that will be smaller if the character has moved a large distance since the last drawn frame.
            while (Math.abs(smallVector[X]) > Block.TILEWIDTH || Math.abs(smallVector[Y]) > Block.TILEHEIGHT) { // if our vector is bigger than the widht or height of one block
                smallVector[X] /= 2; // divide both x & y values in two.
                smallVector[Y] /= 2;
            }
            float[] partialVector = smallVector.clone();

            foundCollision = false;
            foundOverBlock = false;
            while (Math.abs(partialVector[X]) <= Math.abs(vector[X]) && !foundCollision) { // while the partial vector is smaller than the 'real' vector and we haven't found any collisions, we will check all significant positions for collision from earliest to newest position.
                float[] partialPos = {oldPos[X] + partialVector[X], oldPos[Y] + partialVector[Y]}; // the partial position is the actual position the player should be in.
                float[] topLeftPos = {oldPos[X] + partialVector[X] + XMARGIN, oldPos[Y] + partialVector[Y] + YMARGIN}; // the topLeft position is the same at the partial one, I use both to not create any confusions regarding the names.
                float[] topRightPos = {oldPos[X] + partialVector[X] + Animations.width - XMARGIN, oldPos[Y] + partialVector[Y] + YMARGIN};
                float[] botRightPos = {oldPos[X] + partialVector[X] + Animations.width - XMARGIN, oldPos[Y] + partialVector[Y] + Animations.height};
                float[] botLeftPos = {oldPos[X] + partialVector[X] + XMARGIN, oldPos[Y] + partialVector[Y] + Animations.height};

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
                                if (ySpeed < 0 && block.isTopTile(botRightPos)) {
                                    setY(block.getTilePixelPos(botLeftPos)[Y]);
                                } else if (ySpeed < 0) {
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

                if (vector[X] == 0) { // stops an infinite loop caused by the character not moving and the game trying recheck the currentAnimation position.
                    break;
                }
            }

        } else if (dead) { // If the player is dead
            MusicPlayer.pause();
            if (!onGround) {
                Animations.width *= deathScale;
                Animations.height *= deathScale;
            } else {
                xSpeed *= 0.001f; //xSpeed is negative when you're dead, so this will lower the negative speed untill it almost reaches zero.
            }
        }
    }

    /**
     * Renders the currentAnimation player animation on the right x and y values.
     */
    @Override
    public void render() {
        animations.draw(x, y);
    }
    
    /**
     * Kills the player and changes the players currentAnimation position.
     *
     * @param pos The position to change to.
     * @param onGround A boolean that decides if the player should doe on the
     * ground or if the player
     */
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

    /**
     * Sets the player on ground. Changes the onGround variable to true and sets
 the currentAnimation ySpeed to 0.
     */
    public void setOnGround() {
        onGround = true;
        jumpButtonReleased = false;
        ySpeed = 0;
    }

    /**
     * Sets the player on ground depending on the input arguments. Changes the
 onGround variable to true and sets the currentAnimation ySpeed to 0.
     *
     * @param pos The player position to use to find the right position.
     * @param block The block used to get the right position from.
     * @param leftPos A boolean used to calculate the right position.
     */
    public void setOnGround(float[] pos, Block block, boolean leftPos) {
        if (leftPos) {
            setX(pos[X] - XMARGIN);
        } else {
            setX(pos[X] + XMARGIN - Animations.width);
        }

        setY(block.getTilePixelPos(pos)[Y] - Animations.height - 1);
        onGround = true;
        jumpButtonReleased = false;
        ySpeed = 0;
    }

    /**
     * Sets the player under a roof depending on the input arguments. Sets
     * ySpeed to 0;
     *
     * @param pos The player position to use to find the right position.
     * @param block The block used to get the right position from.
     */
    public void setBelowRoof(float[] pos, Block block) {
        setX(pos[X] - XMARGIN); // change x position to the position of the partial vector.
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
     * @param lastBlockIndex Used to check if the index is the last block-index,
     * if so, then sets the players onGround variable to false;
     * @param index
     */
    public void groundCheck(Block block, float[] botLeft, float[] botRight, int lastBlockIndex, int index) {
        if (onGround && (block.inXRangeOfBlock(botLeft) || block.inXRangeOfBlock(botRight))) {
            foundOverBlock = true;
            if (!block.isColliding(new float[]{botLeft[X], botLeft[Y] + block.getTiledHeight() / 2})
                    && !block.isColliding(new float[]{botRight[X], botRight[Y] + block.getTiledHeight() / 2})) {
                this.onGround = false;
            }
        } else if (onGround && index == lastBlockIndex) {
            this.onGround = false;
        }
    }

    /**
     * @return Returns the maximum jump height that the player can achieve.
     */
    public static int getMaxJumpHeight() {
        int output = 0;
        for (float i = JUMPHEIGHT; i > 0; i -= SLOWFALL) {
            output += i;
        }
        return output;
    }

    /**
     * @return Returns the players x value.
     */
    public float getX() {
        return x;
    }

    /**
     * @param x Changes the players x-value to this value.
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * @return Returns the players y value.
     */
    public float getY() {
        return y;
    }

    /**
     * @param y Changes the players y-value to this value.
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return Returns the players xSpeed variable. Which is the currentAnimation change
 in the x-axis for the player each update.
     */
    public float getxSpeed() {
        return xSpeed;
    }

    /**
     * @param xSpeed Changes the players xSpeed variable to this value.
     */
    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * @return Returns the players ySpeed variable. Which is the currentAnimation change
 in the y-axis for the player each update.
     */
    public float getySpeed() {
        return ySpeed;
    }

    /**
     * @param ySpeed Changes the players ySpeed variable to this value.
     */
    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     * @return Returns the players x-axis acceleration.
     */
    public float getxAcc() {
        return xAcc;
    }

    /**
     * @param xAcc Changes the players x-axis acceleration to this value.
     */
    public void setxAcc(float xAcc) {
        this.xAcc = xAcc;
    }

    /**
     * @return Returns the players y-axis acceleration.
     */
    public float getyAcc() {
        return yAcc;
    }

    /**
     * @param yAcc Changes the players y-axis acceleration to this value.
     */
    public void setyAcc(float yAcc) {
        this.yAcc = yAcc;
    }

    /**
     * @return Returns True if the player is currently on the ground. otherwise
     * it returns false.
     */
    public boolean isOnGround() {
        return onGround;
    }

    /**
     * @return Returns True if the player is currently on the sliding. otherwise
     * it returns false.
     */
    public boolean isSliding() {
        return sliding;
    }
}
