/**
 * The business logic of a Parking Pay Station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
package edu.temple.cis.paystation;

import java.util.Map;

public interface PayStation {

    /**
     * Insert coin into the pay station and adjust state accordingly.
     *
     * @param coinValue is an integer value representing the coin in cent. That
     * is, a quarter is coinValue=25, etc.
     * @throws IllegalCoinException in case coinValue is not a valid coin value
     */
    public void addPayment(int coinValue) throws IllegalCoinException;

    /**
     * Read the machine's display. The display shows a numerical description of
     * the amount of parking time accumulated so far based on inserted payment.
     *
     * @return the number to display on the pay station display
     */
    public int readDisplay();

    /**
     * Buy parking time. Terminate the ongoing transaction and return a parking
     * receipt. A non-null object is always returned.
     *
     * @return a valid parking receipt object.
     */
    public Receipt buy();

    /**
     * Cancel the present transaction. Resets the machine for a new transaction.
     */
    public Map cancel();

    /**
    * method to be called when machine needs to be emptied of money
    */
    public int empty();
    
    //NEW added method init coins, this method, when called resets the Map's(<key,values>) values to 0
    public void initCoins();
}


    