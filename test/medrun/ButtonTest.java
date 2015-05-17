/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Admin
 */
public class ButtonTest {

    static Button instance;
    String methodName;

    public ButtonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws SlickException {
        instance = new Button("test", 50, 50, true);
    }

    public String errorMessage(String methodName, Object expected, Object actual) {
        return ("Error in method " + methodName + ". Expected: " + expected + ", got: " + actual);
    }

    public int randint() {
        return (int) (Math.random()*100);
    }

    /**
     * Test of togglePress method, of class Button.
     */
    @Test
    public void testTogglePress() {
        methodName = "togglePress";
        System.out.println(methodName);
        boolean expResult = false;
        boolean result = instance.click;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
        instance.togglePress();
        expResult = true;
        result = instance.click;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
        instance.click = false;
    }

    /**
     * Test of isInButton method, of class Button.
     */
    @Test
    public void testIsInButton() {
        methodName = "isInButton";
        System.out.println(methodName);
        float x = instance.width / 2;
        float y = instance.height / 2;
        boolean expResult = true;
        boolean result = instance.isInButton(x, y);
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of getTitle method, of class Button.
     */
    @Test
    public void testGetTitle() {
        methodName = "getTitle";
        System.out.println(methodName);
        String expResult = "test";
        String result = instance.getTitle();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of setTitle method, of class Button.
     */
    @Test
    public void testSetTitle() {
        methodName = "setTitle";
        System.out.println(methodName);
        String expResult = "testing";
        instance.setTitle(expResult);
        String result = instance.title;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
        instance.title = "test";
    }

    /**
     * Test of getX method, of class Button.
     */
    @Test
    public void testGetX() {
        methodName = "getX";
        System.out.println(methodName);
        float expResult = 50;
        float result = instance.getX();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
    }

    /**
     * Test of setX method, of class Button.
     */
    @Test
    public void testSetX() {
        methodName = "setX";
        System.out.println(methodName);
        float expResult = randint();
        instance.setX(expResult);
        float result = instance.x;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
        instance.x = 50;
    }

    /**
     * Test of getY method, of class Button.
     */
    @Test
    public void testGetY() {
        methodName = "getY";
        System.out.println(methodName);
        float expResult = 50;
        float result = instance.getY();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
    }

    /**
     * Test of setY method, of class Button.
     */
    @Test
    public void testSetY() {
        methodName = "setY";
        System.out.println(methodName);
        float expResult = randint();
        instance.setY(expResult);
        float result = instance.y;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
        instance.y = 50;
    }

    /**
     * Test of getWidth method, of class Button.
     */
    @Test
    public void testGetWidth() {
        methodName = "getWidth";
        System.out.println(methodName);
        float expResult = 100;
        float result = instance.getWidth();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
    }

    /**
     * Test of setWidth method, of class Button.
     */
    @Test
    public void testSetWidth() {
        methodName = "setWidth";
        System.out.println(methodName);
        float expResult = randint();
        instance.setWidth(expResult);
        float result = instance.width;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
        instance.width = 100;
    }

    /**
     * Test of getHeight method, of class Button.
     */
    @Test
    public void testGetHeight() {
        methodName = "getHeight";
        System.out.println(methodName);
        float expResult = 100;
        float result = instance.getHeight();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
    }

    /**
     * Test of setHeight method, of class Button.
     */
    @Test
    public void testSetHeight() {
        methodName = "setHeight";
        System.out.println(methodName);
        float expResult = randint();
        instance.setHeight(expResult);
        float result = instance.height;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
        instance.height = 100;
    }

    /**
     * Test of isClick method, of class Button.
     */
    @Test
    public void testIsClick() {
        methodName = "isClick";
        System.out.println(methodName);
        boolean expResult = false;
        boolean result = instance.isClick();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of setClick method, of class Button.
     */
    @Test
    public void testSetClick() {
        methodName = "setClick";
        System.out.println(methodName);
        boolean expResult = true;
        instance.setClick(expResult);
        boolean result = instance.click;
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
        instance.click = false;
    }

}
