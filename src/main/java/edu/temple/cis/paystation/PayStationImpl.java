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
    public float totalInMachine = 0;   /* total amount of money collected since last emptying */
    
    // Map<i,j> , where i = coin type and j = number of types
    public Map<Integer, Integer> coinMap = new HashMap<Integer, Integer>(); 

    //initCoins sets all the values of the keys and 0(to avoid null) back to 0 when called
    @Override
    public void initCoins() {
    
        coinMap.put(0, 0); //init 0,0 default
        coinMap.put(5, 0); //init 5cents = 0 amount in machine ..
        coinMap.put(10, 0); //init key 10cent, zero in machine so far
        coinMap.put(25, 0); //init key to 25cent, zero in machine so far
   
    }//end initCoins()

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5:
                coinMap.put(coinValue, coinMap.getOrDefault(5, 0) + 1); //this will keep a record of coins entered by person
                break;
            case 10:
                coinMap.put(coinValue, coinMap.getOrDefault(10, 0) + 1);
                break;
            case 25:
                coinMap.put(coinValue, coinMap.getOrDefault(25, 0) +1);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }//end switch statement
        
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
        
        reset(); //reset properties to 0
        
        initCoins(); //method call to clear map back to 0
        
        return r; //return receipt var
    }

//****************************************************************************** 
    /*
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
    @Override
    public Map cancel() {

        reset(); //reset properties to 0
        
        Map<Integer, Integer> new_map = new HashMap<Integer, Integer>(); //create new map to get copied values

        // using iterator to copy values before calling initCoins() to clear Map
        for (Map.Entry<Integer, Integer> entry : coinMap.entrySet()) {
            new_map.put(entry.getKey(),
                    entry.getValue());
        }//end for loop

        initCoins(); //clear map in call to cancel
        
        return new_map;
 

    }//end cancel()

    private void reset() {
        timeBought = insertedSoFar = 0; //resets  properties to 0
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
