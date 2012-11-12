package com.google.code.atpfx.strategy;

import com.google.code.atpfx.data.Pair;

/**

 Describes a strategy of type "time"
 A TimeStrategy executes a trade on a given forward timestamp, waits for the given time and then
 closes the trade.

 * User: Cosmin
 * Date: Apr 13, 2011
 * Time: 7:56:01 PM
 
 */
public interface TimeStrategy extends Runnable {

    void init(Pair tradePair, long enterDate, long endDate, double pipsAmount);
   
}