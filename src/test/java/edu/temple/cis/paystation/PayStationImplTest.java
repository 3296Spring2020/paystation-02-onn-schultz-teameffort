/**
 * Testcases for the Pay Station system.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
//THIS SHOULD DISPLAY WHILE IN ONNBRANCH -- Test to check for recognizability
package edu.temple.cis.paystation;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.Map;

public class PayStationImplTest {

    PayStation ps;
    PayStationImpl Mapcoin = new PayStationImpl();

    @Before
    public void setup() {
        ps = new PayStationImpl();
        ps.initCoins();
        Mapcoin.initCoins();
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents", 2, ps.readDisplay());
    }

    /**
     * Entering 25 cents should make the display report 10 minutes parking time.
     */
    @Test
    public void shouldDisplay10MinFor25Cents() throws IllegalCoinException {
        ps.addPayment(25);
        assertEquals("Should display 10 min for 25 cents",
                10, ps.readDisplay());
    }

    /**
     * Verify that illegal coin values are rejected.
     */
    @Test(expected = IllegalCoinException.class)
    public void shouldRejectIllegalCoin() throws IllegalCoinException {
        ps.addPayment(17);
    }

    /**
     * Entering 10 and 25 cents should be valid and return 14 minutes parking
     */
    @Test
    public void shouldDisplay14MinFor10And25Cents()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Should display 14 min for 10+25 cents",
                14, ps.readDisplay());
    }

    /**
     * Buy should return a valid receipt of the proper amount of parking time
     */
    @Test
    public void shouldReturnCorrectReceiptWhenBuy()
            throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(10);
        ps.addPayment(25);
        Receipt receipt;
        receipt = ps.buy();
        assertNotNull("Receipt reference cannot be null",
                receipt);
        assertEquals("Receipt value must be 16 min.",
                16, receipt.value());
    }

    /**
     * Buy for 100 cents and verify the receipt
     */
    @Test
    public void shouldReturnReceiptWhenBuy100c()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(10);
        ps.addPayment(25);
        ps.addPayment(25);

        Receipt receipt;
        receipt = ps.buy();
        assertEquals(40, receipt.value());
    }

    /**
     * Verify that the pay station is cleared after a buy scenario
     */
    @Test
    public void shouldClearAfterBuy()
            throws IllegalCoinException {
        ps.addPayment(25);
        ps.buy(); // I do not care about the result
        // verify that the display reads 0
        assertEquals("Display should have been cleared",
                0, ps.readDisplay());
        // verify that a following buy scenario behaves properly
        ps.addPayment(10);
        ps.addPayment(25);
        assertEquals("Next add payment should display correct time",
                14, ps.readDisplay());
        Receipt r = ps.buy();
        assertEquals("Next buy should return valid receipt",
                14, r.value());
        assertEquals("Again, display should be cleared",
                0, ps.readDisplay());
    }

    /**
     * Verify that cancel clears the pay station
     */
    @Test
    public void shouldClearAfterCancel()
            throws IllegalCoinException {
        ps.addPayment(10);
        ps.cancel();
        assertEquals("Cancel should clear display",
                0, ps.readDisplay());
        ps.addPayment(25);
        assertEquals("Insert after cancel should work",
                10, ps.readDisplay());
    }

    /**
     * Test for reporting the money taken in since last time empty was called
     */
    @Test
    public void ShouldReportAfterMoneyEmptied()
            throws IllegalCoinException {
        /* add some fake payment to test the buy before emptying*/
        ps.addPayment(10);
        /* here we use the 10 cents to buy some time*/
        ps.buy();
        /* even though we add more money this should not be reported*/
        ps.addPayment(10);
        /* money removed from the machine should be only from what was spent.*/
        int moneyRemoved = ps.empty();
        assertEquals("this should print 10", 10, moneyRemoved);

    } // end ShouldReportAfterMoneyEmptied

    /**
     * This should return empty after money is emptied from machine
     *
     * @throws IllegalCoinException
     */
    @Test
    public void ShouldReportEmptyMachine()
            throws IllegalCoinException {
        /* money in machine should be reset to 0 after emptied */
 /* add some fake payment to test the buy before emptying*/
        PayStationImpl inst = new PayStationImpl();
        int amountToAdd = 25;
        inst.addPayment(amountToAdd);
        inst.empty();
        int result = (int) inst.totalInMachine;
        assertEquals("this should print 0", 0, result);
    } // end ShouldReportEmptyMachine

    /**
     * Testing cancel function returns a hash map with a single coin after a
     * single coin is inserted
     */
    @Test
    public void cancelReturnsOneCoin()
            throws IllegalCoinException {

        ps.addPayment(5);
        /* we expect to return a map with 1 nickel in it, so we get the instance of the
        * pay machine and get the count of all coins with value 5 (ie all nickels) */
        assertEquals(" this should be 1 nickel", 1, ps.cancel().get(5));

    } // end cancelReturnsOneCoin
    /**
     * Testing that coins that are canceled do not add to total amount
     * in machine.
     */
    @Test
    public void canceledCoinAreNotCounted()
            throws IllegalCoinException {

        PayStationImpl inst = new PayStationImpl();
        inst.addPayment(5);
        Map temp = inst.cancel();
        assertEquals("nothing in machine, this should be 0", 0, (int) inst.totalInMachine);
    } // end canceledCoinAreNotCounted

    /**
     * Testing that cancel can take in a variety of coin types
     * and return the correct denominations when called.
     */
    @Test
    public void returnMixtureOfCoins() throws IllegalCoinException {
        ps.addPayment(5);
        ps.addPayment(5);
        ps.addPayment(10);

        Map temp = ps.cancel();

        assertEquals("should return 2 nickels", 2, (int) temp.get(5));
        assertEquals("should return 1 dime", 1, (int) temp.get(10));

    }// end returnMixtureOfCoins

    /**
     * Testing that cancel can take in a variety of coin types
     * and return the correct denominations when called. even if they total
     * an amount equal to another denomination. IE 5 nickels canceled should give you back
     * 5 nickles not 1 quarter.
     */
    @Test
    public void cancelReturnsCorrectCoins()
            throws IllegalCoinException {
        /* here we add twenty five cents*/
        ps.addPayment(5);
        ps.addPayment(5);
        ps.addPayment(5);
        ps.addPayment(10);

        /* the map should return the correct coin denominations not just one 25 cent coin*/
        Map temp = ps.cancel();

        /* we expect 3 nickels and one dime  and 0 quarters*/
        assertEquals("should return 3 nickels", 3, (int) temp.get(5));
        assertEquals("should return 1 dime", 1, (int) temp.get(10));
        assertEquals("should return 0 Quarters", 0, (int) temp.get(25));

    }//end cancelReturnsCorrectCoins()

    /**
     * Testing that cancel clears the Hash map containing the
     * record of what coins were received.
     */
    @Test
    public void cancelClearsMap()
            throws IllegalCoinException {
        PayStationImpl inst = new PayStationImpl();
        /* here we add twenty five cents*/
        inst.addPayment(5);
        inst.addPayment(5);
        inst.addPayment(5);
        inst.addPayment(10);

        /* the map should return the correct coin denominations not just one 25 cent coin*/
        Map temp = inst.cancel();

        /*check that all types of coins in maps are set to zero*/
        assertEquals("count of nickels should be 0", (int) inst.coinMap.get(5), 0);
        assertEquals("count of dimes should be 0", (int) inst.coinMap.get(10), 0);
        assertEquals("count of quarters should be 0", (int) inst.coinMap.get(25), 0);

    } // end cancelClearsMap

//******************************************************************************
/*Test method below named buyClearsMap() creates an instance of type PayStationImpl,
 *The test then "adds"  2 U.S. quarters, 1 U.S. nickel, and 1 U.S. dime
 *Then "prints" the receipt by calling instance.buy(), which in result will clear the coinMap.
 *There are 3 assertEquals test to make sure that, the coins inserted are then cleared after call to buy  
 */ 
//******************************************************************************    
    @Test
    public void buyClearsMap()
            throws IllegalCoinException {
        
        PayStationImpl inst = new PayStationImpl();
               
        inst.addPayment(25); //add 25 cents
        inst.addPayment(25); //add 25 cents
        inst.addPayment(5); //add 5 cents
        inst.addPayment(10); //add 10 cents

        Receipt temp = inst.buy(); //Create a temp of type Receipt while resetting coinMap to 0 with buy() call
        

        //Test to check buy() clears map
        assertEquals("count of nickels should be 0", (int) inst.coinMap.get(5), 0);
        assertEquals("count of dimes should be 0", (int) inst.coinMap.get(10), 0);
        assertEquals("count of quarters should be 0", (int) inst.coinMap.get(25), 0);

    }//end buyClearsMap

}
