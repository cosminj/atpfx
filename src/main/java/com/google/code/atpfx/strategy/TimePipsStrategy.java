package com.google.code.atpfx.strategy;

import com.google.code.atpfx.data.Pair;

/**

 Describes a strategy of type "time" + "pips"
 A TimePipsStrategy executes a trade on a given forward timestamp, waits for the given "pips" to be accomplished
 and closes the trade either when the pips are accomplished or when the stop loss if passed.
 
 * User: Cosmin
 * Date: Apr 13, 2011
 * Time: 8:40:12 PM
 */
public interface TimePipsStrategy {
    
    void init(Pair tradePair, long enterDate, double pipsAmount, double stopLoss);
}