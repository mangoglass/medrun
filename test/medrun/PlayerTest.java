

package medrun;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tom Axblad
 */
public class PlayerTest {
    
    static Player instance;
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        instance = new Player(0,0,true);
    }

    /**
     * Test of die method, of class Player.
     */
    @Test
    public void testDie() {
        System.out.println("die");
        float[] pos = {0,0};
        boolean onGround = false;
        instance.die(pos, onGround);
        boolean expResult = true;
        boolean result = instance.dead;
        assertEquals(expResult, result);
    }

    /**
     * Test of setOnGround method, of class Player.
     */
    @Test
    public void testSetOnGround() {
        System.out.println("setOnGround");
        instance.onGround = false;
        instance.setOnGround();
        boolean expResult = true;
        boolean result = instance.onGround;
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaxJumpHeight method, of class Player.
     */
    @Test
    public void testGetMaxJumpHeight() {
        System.out.println("getMaxJumpHeight");
        int expResult = 324;
        int result = Player.getMaxJumpHeight();
        assertEquals(expResult, result);
    }

    /**
     * Test of getX method, of class Player.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        float expResult = 0;
        float result = instance.getX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setX method, of class Player.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        float expResult = 10;
        instance.setX(expResult);
        float result = instance.x;
        assertEquals(expResult, result, 0.0);
        instance.x = 0;
    }

    /**
     * Test of getY method, of class Player.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        float expResult = 0;
        float result = instance.getY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setY method, of class Player.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        float expResult = 10;
        instance.setY(expResult);
        float result = instance.y;
        assertEquals(expResult, result, 0.0);
        instance.y = 0;
    }

    /**
     * Test of getxSpeed method, of class Player.
     */
    @Test
    public void testGetxSpeed() {
        System.out.println("getxSpeed");
        float expResult = 0;
        float result = instance.getxSpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setxSpeed method, of class Player.
     */
    @Test
    public void testSetxSpeed() {
        System.out.println("setxSpeed");
        float expResult = 10;
        instance.setxSpeed(expResult);
        float result = instance.xSpeed;
        assertEquals(expResult, result, 0.0);
        instance.xSpeed = 0;
    }

    /**
     * Test of getySpeed method, of class Player.
     */
    @Test
    public void testGetySpeed() {
        System.out.println("getySpeed");
        float expResult = 0;
        float result = instance.getySpeed();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setySpeed method, of class Player.
     */
    @Test
    public void testSetySpeed() {
        System.out.println("setySpeed");
        float expResult = 10;
        instance.setySpeed(expResult);
        float result = instance.ySpeed;
        assertEquals(expResult, result, 0.0);
        instance.ySpeed = 0;
    }

    /**
     * Test of getxAcc method, of class Player.
     */
    @Test
    public void testGetxAcc() {
        System.out.println("getxAcc");
        float expResult = 0;
        float result = instance.getxAcc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setxAcc method, of class Player.
     */
    @Test
    public void testSetxAcc() {
        System.out.println("setxAcc");
        float expResult = 10;
        instance.setxAcc(expResult);
        float result = instance.xAcc;
        assertEquals(expResult, result, 0.0);
        instance.xAcc = 0;
    }

    /**
     * Test of getyAcc method, of class Player.
     */
    @Test
    public void testGetyAcc() {
        System.out.println("getyAcc");
        float expResult = 0;
        float result = instance.getyAcc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setyAcc method, of class Player.
     */
    @Test
    public void testSetyAcc() {
        System.out.println("setyAcc");
        float expResult = 10;
        instance.setyAcc(expResult);
        float result = instance.yAcc;
        assertEquals(expResult, result, 0.0);
        instance.yAcc = 0;
    }

    /**
     * Test of isOnGround method, of class Player.
     */
    @Test
    public void testIsOnGround() {
        System.out.println("isOnGround");
        instance.onGround = true;
        boolean expResult = true;
        boolean result = instance.isOnGround();
        assertEquals(expResult, result);
        instance.onGround = false;
        expResult = false;
        result = instance.isOnGround();
        assertEquals(expResult, result);
    }

    /**
     * Test of isSliding method, of class Player.
     */
    @Test
    public void testIsSliding() {
        System.out.println("isSliding");
        instance.sliding = true;
        boolean expResult = true;
        boolean result = instance.isSliding();
        assertEquals(expResult, result);
        instance.sliding = false;
        expResult = false;
        result = instance.isSliding();
        assertEquals(expResult, result);
    }
}
