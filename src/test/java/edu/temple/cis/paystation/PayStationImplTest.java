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

public class PayStationImplTest {

    PayStation ps;
    PayStationImpl Mapcoin = new PayStationImpl();
    
    @Before
    public void setup() {
        ps = new PayStationImpl();
        
        Mapcoin.coinMap.put(0, 0); //init 0,0 default
        Mapcoin.coinMap.put(5, 0); //init 5cents = 0 amount in machine ..
        Mapcoin.coinMap.put(10, 0); //init key 10cent, zero in machine so far
        Mapcoin.coinMap.put(25, 0); //init key to 25cent, zero in machine so far
        //every coinmap.put method here will/may be moved to an appropiate spot
    }

    /**
     * Entering 5 cents should make the display report 2 minutes parking time.
     */
    @Test
    public void shouldDisplay2MinFor5Cents()
            throws IllegalCoinException {
        ps.addPayment(5);
        assertEquals("Should display 2 min for 5 cents",
                2, ps.readDisplay());
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
            throws IllegalCoinException{
        /* add some fake payment to test the buy before emptying*/
        ps.addPayment(10);
        /* here we use the 10 cents to buy some time*/
        ps.buy();
        /* money removed from the machine*/
        int moneyRemoved = ps.empty();
        assertEquals("this should print 10"  , 10 ,moneyRemoved);

    }


    /**
     * This should return empty after money is emptied from machine
     * @throws IllegalCoinException
     */
    @Test
    public void ShouldReportEmptyMachine()
            throws IllegalCoinException{
        /* money in machine should be reset to 0 after emptied */
        /* add some fake payment to test the buy before emptying*/
        PayStationImpl inst = new PayStationImpl();
        int amountToAdd = 25;
        inst.addPayment(amountToAdd);
        inst.empty();
        int result = (int)inst.totalInMachine;
        assertEquals("this should print 0"  , 0 ,result);
    }
}
