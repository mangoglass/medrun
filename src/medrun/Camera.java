/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

/**
 *
 * @author Admin
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

    public Camera() {
        started = false;
        xAcc = 3;
        yAcc = 0;
    }

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
