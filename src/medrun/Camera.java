/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

/**
 * The camera object is the object that decides how the world will transform in
 * relation to the players position.
 *
 * @author Tom Axblad
 */
public class Camera {

    final static int yMargin = 0;
    final static int yOffset = Medrun.height / 2;
    final static int yDiviator = 10;
    final static int startSpeed = (int) (5 * Medrun.difficulty);
    final static float yAccSpeed = 0.1f;

    float xAcc;
    float yAcc;
    float xTarget;
    float yTarget;
    float xDiff;
    float yDiff;

    boolean caughtUp;

    public static boolean started;

    /**
     * The constructor for the Camera object, creates a camera object and sets
     * objects variables accordingly.
     */
    public Camera() {
        started = false;
        xAcc = 3;
        yAcc = 0;
    }

    /**
     * The update function for the camera object. The camera objects moves in
     * relation to the player position, the time that the game has been running
     * for. And the deltaRation, a variable representing the amount of change
     * between this frame and the last frame.This function will run every frame.
     *
     * @param gameTime a variable representing the time the game has been
     * running for measured in milliseconds.
     * @param deltaRatio the variable representing the amount of change between
     * this frame and the last frame.
     * @param player the player object that is checked to move the camera to the
     * right positions at the right time.
     */
    public void update(int gameTime, float deltaRatio, Player player) {

        if (gameTime > 2000 && !started) {
            started = true;
            player.animations.current = player.animations.getAnimations()[Animations.RUN];
        }

        if (started && !player.dead) {
            yTarget = player.y;
            yDiff = yTarget - (GameState.translatedY + yOffset);

            if (yDiff != 0) {
                yAcc = (yDiff / yDiviator) * deltaRatio;
            }
            if (xAcc < startSpeed) {
                xAcc += (0.05f) * deltaRatio;
            } else if (xAcc > startSpeed) {
                xAcc = startSpeed;
            }

            GameState.dTranslatedX = ((gameTime / (15000 / Medrun.difficulty)) + xAcc) / GameState.timeFlow;
            GameState.dTranslatedY = yAcc / GameState.timeFlow;
        } else if (player.dead) {
            if (GameState.dTranslatedX > 0.001 || GameState.dTranslatedX < -0.001) {
                GameState.dTranslatedX /= 1.05;
            } else {
                GameState.dTranslatedX = 0;
            }
            if (GameState.dTranslatedY > 0.001 || GameState.dTranslatedY < -0.001) {
                GameState.dTranslatedY /= 1.05;
            } else {
                GameState.dTranslatedY = 0;
            }
        }
    }
}
