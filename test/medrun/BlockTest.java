/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medrun;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Admin
 */
public class BlockTest {
    
    Block testBlock;
    Random random;
    public BlockTest() throws SlickException {
        random = new Random();
        testBlock = new Block("test/testBlock.tmx");
    }
    
    public String errorMessage(String methodName, Object expected, Object actual){
        return ("Error in method " + methodName + ". expected: " + expected + ", actual: " + actual);
    }
    
    public int randint(){
        return random.nextInt(100);
    }
    
    public float randflt(){
        return (float)random.nextInt(100);
    }

    /**
     * Test of getType method, of class Block.
     */
    @Test
    public void testGetType() {
        String methodName = "getType";
        System.out.println(methodName);
        Block instance = testBlock;
        int type = randint();
        int expResult = type;
        instance.type = type;
        int result = instance.getType();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of getX method, of class Block.
     */
    @Test
    public void testGetX() {
        String methodName = "getX";
        System.out.println(methodName);
        Block instance = testBlock;
        int x = randint();
        instance.x = x;
        float expResult = x;
        float result = instance.getX();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
    }

    /**
     * Test of getY method, of class Block.
     */
    @Test
    public void testGetY() {
        String methodName = "getY";
        System.out.println(methodName);
        Block instance = testBlock;
        int y = randint();
        instance.y = y;
        float expResult = y;
        float result = instance.getY();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result, 0.0);
    }

    /**
     * Test of getTiledWidth method, of class Block.
     */
    @Test
    public void testGetTiledWidth() {
        String methodName = "getTiledWidth";
        System.out.println(methodName);
        Block instance = testBlock;
        int expResult = instance.tiledMap.getTileWidth();
        int result = instance.getTiledWidth();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of getTiledHeight method, of class Block.
     */
    @Test
    public void testGetTiledHeight() {
        String methodName = "getTiledHeight";
        System.out.println(methodName);
        Block instance = testBlock;
        int expResult = instance.tiledMap.getTileWidth();
        int result = instance.getTiledHeight();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of getWidth method, of class Block.
     */
    @Test
    public void testGetWidth() {
        String methodName = "getWidth";
        System.out.println(methodName);
        Block instance = testBlock;
        int expResult = instance.tiledMap.getWidth() * instance.tiledMap.getTileWidth();
        int result = instance.getWidth();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of getHeight method, of class Block.
     */
    @Test
    public void testGetHeight() {
        String methodName = "getHeight";
        System.out.println(methodName);
        Block instance = testBlock;
        int expResult = instance.tiledMap.getHeight() * instance.tiledMap.getTileHeight();
        int result = instance.getHeight();
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of inBlock method, of class Block.
     */
    @Test
    public void testInBlock() {
        String methodName = "inBlock";
        System.out.println(methodName);
        Block instance = testBlock;
        float[] pos = {random.nextInt(instance.tiledMap.getWidth() * instance.tiledMap.getTileWidth()), random.nextInt(instance.tiledMap.getHeight() * instance.tiledMap.getTileHeight())};
        boolean expResult = true;
        boolean result = instance.inBlock(pos);
        assertEquals(errorMessage(methodName, expResult, result), expResult, result);
    }

    /**
     * Test of isLeftmostTile method, of class Block.
     */
    @Test
    public void testIsLeftmostTile() {
        String methodName = "isLeftmostTile";
        System.out.println(methodName);
        Block instance = testBlock;
        float[] pos = {10, instance.tiledMap.getTileHeight()*3};
        boolean expResult = false;
        boolean result = instance.isLeftmostTile(pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of isRightmostTile method, of class Block.
     */
    @Test
    public void testIsRightmostTile() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("isRightmostTile");
        float[] pos = null;
        Block instance = null;
        boolean expResult = false;
        boolean result = instance.isRightmostTile(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTopTile method, of class Block.
     */
    @Test
    public void testIsTopTile() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("isTopTile");
        float[] pos = null;
        Block instance = null;
        boolean expResult = false;
        boolean result = instance.isTopTile(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBottomTile method, of class Block.
     */
    @Test
    public void testIsBottomTile() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("isBottomTile");
        float[] pos = null;
        Block instance = null;
        boolean expResult = false;
        boolean result = instance.isBottomTile(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isColliding method, of class Block.
     */
    @Test
    public void testIsColliding() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("isColliding");
        float[] pos = null;
        Block instance = null;
        boolean expResult = false;
        boolean result = instance.isColliding(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTilePixelPos method, of class Block.
     */
    @Test
    public void testGetTilePixelPos() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("getTilePixelPos");
        float[] pos = null;
        Block instance = null;
        float[] expResult = null;
        float[] result = instance.getTilePixelPos(pos);
        assertEquals(Arrays.toString(expResult), Arrays.toString(result));
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileMapPos method, of class Block.
     */
    @Test
    public void testGetTileMapPos() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("getTileMapPos");
        float[] pos = null;
        Block instance = null;
        int[] expResult = null;
        int[] result = instance.getTileMapPos(pos);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inXRangeOfBlock method, of class Block.
     */
    @Test
    public void testInXRangeOfBlock() {
        String methodName = "getType";
        System.out.println(methodName);
        System.out.println("inXRangeOfBlock");
        float[] pos = null;
        Block instance = null;
        boolean expResult = false;
        boolean result = instance.inXRangeOfBlock(pos);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
