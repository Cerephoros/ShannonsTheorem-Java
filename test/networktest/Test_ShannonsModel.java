package networktest;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import network.ShannonsModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A series of ShannonsModel test cases are documented here to ensure the quality
 * of each tests that are run correctly.
 * @author Erik Njolstad
 */
public class Test_ShannonsModel extends TestCase {
    
    /**
     * Default Constructor
     */
    public Test_ShannonsModel() {
        super();
    }
    
    /**
     * This runs a collection of tests from the Test_ShannonsModel class.
     * @return new TestSuite(ShannonsThoremTest.class)
     */
     public static TestSuite suite() {
        return new TestSuite(Test_ShannonsModel.class);
    }
    
     /**
     * Starts up the Test_ShannonsModel test cases.
     * @throws Exception 
     */
    @Override @Before
    public void setUp() throws Exception {
        System.out.println("Start up Test_ShannonsModel");
    }
    
    /**
     * Finishes the Test_ShannonsModel test cases.
     * @throws Exception 
     */
     @Override @After
    public void tearDown() throws Exception {
        System.out.println("End Test_ShannonsModel\n");
    }
    
    /**
     * Test of getBandwidth method, of class ShannonsModel.
     */
    @Test
    public void testGetBandwidth() {
        System.out.println("getBandwidth");
        ShannonsModel instance = new ShannonsModel(500, 120);
        double expResult = 500.0;
        double result = instance.getBandwidth();
        assertEquals(expResult, result, 0.0);
        if (result != expResult) {
        fail("This calculation is incorrect. Check your code.");
        }
    }

    /**
     * Test of getSignalToNoise method, of class ShannonsModel.
     */
    @Test
    public void testGetSignalToNoise() {
        System.out.println("getSignalToNoise");
        ShannonsModel instance = new ShannonsModel(-25, -50);
        double expResult = -50;
        double result = instance.getSignalToNoise();
        assertEquals(expResult, result, 0.0);
        if (result != expResult) {
            fail("This calculation is incorrect. Check your code.");
        }
    }

    /**
     * Test of getMaximumDataRate method, of class ShannonsModel.
     */
    @Test
    public void testGetMaximumDataRate() {
        System.out.println("getMaximumDataRate");
        ShannonsModel instance = new ShannonsModel(90, 120);
        double expResult = 3587.68234;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        if (Math.abs(result + expResult) < 0.00001) {
            fail("This calculation is incorrect. Check your code.");
        }
    }
    
    /**
     * Test of getMaximumDataRate2 method, of class ShannonsModel.
     */
    @Test
    public void testGetMaximumDataRate2() {
        System.out.println("getMaximumDataRate");
        try {
        ShannonsModel instance = new ShannonsModel(0, 200000);
        double expResult = Double.NaN;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        } catch (ArithmeticException e) {
            System.out.println("The maximum data rate does not return a number. " + e);
        }
    }
    
    /**
     * Test of getMaximumDataRate3 method, of class ShannonsModel.
     */
    @Test
    public void testGetMaximumDataRate3() {
        System.out.println("getMaximumDataRate");
        try {
        ShannonsModel instance = new ShannonsModel(4000, 5000);
        double expResult = Double.POSITIVE_INFINITY;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        } catch (ArithmeticException e) {
            System.out.println("The maximum data rate does not return a number. " + e);
        }
    }
    
    /**
     * Test of getMaximumDataRate4 method, of class ShannonsModel.
     */
    @Test
    public void testGetMaximumDataRate4() {
        System.out.println("getMaximumDataRate");
        try {
        ShannonsModel instance = new ShannonsModel(-60, 75);
        double expResult = Double.NaN;
        double result = instance.getMaximumDataRate();
        assertEquals(expResult, result, 0.00001);
        } catch (ArithmeticException e) {
            System.out.println("The maximum data rate does not return a number. " + e);
        }
    }

    /**
     * Test of setBandwidth method, of class ShannonsModel.
     */
    public void testSetBandwidth() {
        System.out.println("setBandwidth");
        ShannonsModel instance = new ShannonsModel();
        Double b = 150.0;
        instance.setBandwidth(b);
        if (instance.getBandwidth() != 150.0) {
            fail("The calculation is incorrect. Check your code.");
        }
    }
    
    /**
     * Test of setBandwidth2 method, of class ShannonsModel.
     */
    public void testSetBandwidth2() {
        System.out.println("setBandwidth");
        try {
        ShannonsModel instance = new ShannonsModel();
        Double b = null;
        instance.setBandwidth(b);
        } catch (NullPointerException e) {
            System.out.println("The value cannot be null. " + e);
        }
    }

    /**
     * Test of setSignalToNoise method, of class ShannonsModel.
     */
    @Test
    public void testSetSignalToNoise() {
        System.out.println("setSignalToNoise");
        Double snr = 50.0;
        ShannonsModel instance = new ShannonsModel();
        instance.setSignalToNoise(snr);
        if (instance.getSignalToNoise() != 50.0) {
            fail("The calculation is incorrect. Check your code.");
        }
    }
    
    /**
     * Test of setSignalToNoise2 method, of class ShannonsModel.
     */
    @Test
    public void testSetSignalToNoise2() {
        System.out.println("setSignalToNoise");
        try {
        ShannonsModel instance = new ShannonsModel();
        instance.setSignalToNoise(Double.parseDouble("abc"));
        } catch (NumberFormatException e) {
            System.out.println("You cannot enter a string. " + e);
        }
    }
    
    /**
     * Test of setSignalToNoise3 method, of class ShannonsModel.
     */
    @Test
    public void testSetSignalToNoise3() {
        System.out.println("setSignalToNoise");
        try {
        ShannonsModel instance = new ShannonsModel();
        instance.setBandwidth(-9.0);
        } catch (IllegalArgumentException e) {
            System.out.println("You cannot enter a negative number. " + e);
        }
        }
}
