/**
 * Implementation of the pay station.
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

import java.util.HashMap;
import java.util.Map; //import Map utilities for method cancel()
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Before;

public class PayStationImpl implements PayStation {

    private int insertedSoFar;
    private int timeBought;

    public float totalInMachine;   /* total amount of money collected since last emptying */
    /* total amount of money collected since last emptying */
    public Map<Integer, Integer> coinMap = new HashMap<Integer, Integer>(); // Map<i,j> , where i = coin type and j = number of types

    

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                coinMap.put(coinValue, coinMap.get(coinValue) +1 ); //this will keep a record of coins entered by person
                break;
            case 10:
                coinMap.put(coinValue, coinMap.get(coinValue) +1 ); //this will keep a record of coins entered by person
                break;
            case 25:
                coinMap.put(coinValue, coinMap.get(coinValue) +1 ); //this will keep a record of coins entered by person
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        

        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        /*each min cost 2.5 cents so we can find the total money spent by 
        multiplyng time bought by 2.5*/
        float moneyIn = timeBought * 2.5f;
        /* add total money recieved each time we buy*/
        totalInMachine += moneyIn;
        reset();
        return r;
    }

//****************************************************************************** 
    /**
     * Cancel the present transaction. Resets the paystation for a new
     * transaction.
     *
     * @return A Map defining the coins returned to the user. The key is the
     * coin type and the associated value is the number of these coins that are
     * returned. The Map object is never null even if no coins are returned. The
     * Map will only contain only keys for coins to be returned. (If you enter
     * two dimes and a nickle, you should get back two dimes and a nickle, not a
     * quarter.) The Map will be cleared after a cancel or buy.
     */
//Map<Integer, Integer> cancel();
//Parameters: K - the type of keys maintained by this map, V - the type of mapped values
    @Override
    public Map cancel() {

        reset();
        
        return coinMap;
 

    }//end cancel()

    private void reset() {
        timeBought = insertedSoFar = 0;
    }

    /*returns the total amount of money collected by the paystation since 
     the last call and empties it, setting the total to zero*/
     @Override
     public int empty(){
     float rtrn;
     rtrn = totalInMachine;
     totalInMachine = 0;
     return (int)rtrn;
     } 

}
