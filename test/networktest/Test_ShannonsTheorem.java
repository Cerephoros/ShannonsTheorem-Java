package networktest;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import network.ShannonsTheorem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A series of ShannonsTheorem test cases are documented here to ensure the quality
 * of each tests that are run correctly.
 * @author Erik Njolstad
 */
public class Test_ShannonsTheorem extends TestCase {
        
    /**
     * Default Constructor
     */
    public Test_ShannonsTheorem() {
        super();
    }
       
    /**
     * This runs a collection of tests from the ShannonsTheoremTest class.
     * @return new TestSuite(ShannonsThoremTest.class)
     */
    public static TestSuite suite() {
        return new TestSuite(Test_ShannonsTheorem.class);
    }
    
    /**
     * Starts up the ShannonsTheoremTest test cases.
     * @throws Exception 
     */
    @Override @Before
    protected void setUp() throws Exception {
            System.out.println("Start up Test_ShannonsTheorem");
    }
    
    /**
     * Finishes the ShannonsTheoremTest test cases.
     * @throws Exception 
     */
    @Override @After
    protected void tearDown() throws Exception {
        System.out.println("End Test_ShannonsTheorem\n");
    }
    
    /**
    * Test of getBandwidth method, of class ShannonsTheorem.
    */
    @Test
    public void testGetBandwidth() {
        System.out.println("getBandwidth");
        ShannonsTheorem instance = new ShannonsTheorem(100, 60);
        double expResult = 100;
        double result = instance.getBandwidth();
        assertEquals(expResult, result, 0.0);
        if (result != expResult) {
        fail("This calculation is incorrect. Check your code.");
        }
    }

    /**
     * Test of getSignalToNoise method, of class ShannonsTheorem.
     */
    @Test
    public void testGetSignalToNoise() {
        System.out.println("getSignalToNoise");
        ShannonsTheorem instance = new ShannonsTheorem(0, 50);
        double expResult = 50;
        double result = instance.getSignalToNoise();
        assertEquals(expResult, result, 0);
        if (result != expResult) {
        fail("This calculation is incorrect. Check your code.");
        }
    }

    /**
     * Test of getMaximumDataRate method, of class ShannonsTheorem.
     */
    @Test
    public void testGetMaximumDataRate1() {
        System.out.println("getMaximumDataRate1");
        ShannonsTheorem instance = new ShannonsTheorem(90, 10);
        double expResult = 311.34884;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        if (Math.abs(expResult + result) < 0.00001) {
            fail("This calculation is incorrect. Check your code.");
        }   
    }
   
    /**
     * Second test of getMaximumDataRate method, of class ShannonsTheorem
     */
    @Test
    public void testGetMaximumDataRate2() {
        System.out.println("getMaximumDataRate2");
        ShannonsTheorem instance = new ShannonsTheorem(10, -20);
        double expResult = Double.NaN;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        if (Math.abs(expResult + result) < 0.00001) {
            fail("This calculation is incorrect. Check your code.");
        }   
    }
    
    /**
     * Third test of getMaximumDataRate method, of class ShannonsTheorem
     */
    @Test
    public void testGetMaximumDataRate3() {
        System.out.println("getMaximumDataRate3");
        ShannonsTheorem instance = new ShannonsTheorem(1200, 150);
        double expResult = 59794.70570;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        if (Math.abs(expResult + result) < 0.00001) {
            fail("This calculation is incorrect. Check your code.");
        }   
    }
    
    /**
     * Fourth test of getMaximumDataRate method, of class ShannonsTheorem
     */
    @Test
    public void testGetMaximumDataRate4() {
        System.out.println("getMaximumDataRate4");
        ShannonsTheorem instance = new ShannonsTheorem(-300, -70);
        double expResult = Double.NaN;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        if (Math.abs(expResult + result) < 0.00001) {
            fail("This calculation is incorrect. Check your code.");
        }   
    }

    /**
     * Test of setBandwidth method, of class ShannonsTheorem.
     */
    @Test
    public void testSetBandwidth() {
        System.out.println("setBandwidth");
        double h = 50;
        ShannonsTheorem instance = new ShannonsTheorem();
        instance.setBandwidth(h);
        if (instance.getBandwidth() != 50) {
        fail("This calculation is incorrect. Check your code.");    
        }
    }

    /**
     * Test of setSignalToNoise method, of class ShannonsTheorem.
     */
    @Test
    public void testSetSignalToNoise() {
        System.out.println("setSignalToNoise");
        double snr = 80;
        ShannonsTheorem instance = new ShannonsTheorem();
        instance.setSignalToNoise(snr);
        if (instance.getSignalToNoise() != 80) {
        fail("This calculation is incorrect. Check your code.");
        }
    }
}
