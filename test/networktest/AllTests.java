package networktest;

import junit.framework.TestSuite;

/**
 * The purpose of this class is to run all of the test cases made available.
 * @author Erik Njolstad
 */
public class AllTests {
   
    /**
     * Parameterized Constructor.
     * @param name 
     */
    public AllTests(String name) {
        
    }
    
    /**
     * Executes and tests all of the available test cases.
     * @param args 
     */
    public static void main (String [] args) {
        junit.textui.TestRunner.run(suite());
    }
   
    /**
     * This runs a collection of all the tests for the ShannonsTheoremTest and
     * ShannonsModelTest classes.
     * @return new TestSuite(ShannonsThoremTest.class)
     */
    public static TestSuite suite() {
        TestSuite test = new TestSuite("Test Cases");
        test.addTestSuite(Test_ShannonsModel.class);
        test.addTestSuite(Test_ShannonsTheorem.class);
        return test;
    }
}
