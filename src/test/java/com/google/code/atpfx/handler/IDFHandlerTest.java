package com.google.code.atpfx.handler;

import com.google.code.atpfx.FXCMConnection;
import com.google.code.atpfx.IConnection;
import com.google.code.atpfx.base.MockConnection;
import com.google.code.atpfx.base.SpringTestBase;
import com.google.code.atpfx.data.Pair;
import com.google.code.atpfx.data.Tick;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class IDFHandlerTest extends SpringTestBase {

    @Test
    public void REAL_testMessageArrived() {
        IDFHandler idfHandler = new IDFHandler();

        IConnection conn = new FXCMConnection();

        conn.addListener(idfHandler);

        conn.open();

        System.out.println("<< >> " + conn.isOpened());

        secondsToWait(5);



        conn.close();


        // check what contains IDFMemoryBuffer

        IDFMemoryBuffer memBuff = IDFMemoryBuffer.getIDFMemoryBuffer();
        System.out.println("#############################################################################");
        System.out.println("IDFMemoryBuffer contains: ");
        System.out.println("Latest ticks:");
        Map<Pair, Tick> latestTicks = memBuff.getLatestTicks();
        for (Pair pair : latestTicks.keySet()) {
            System.out.println(pair + " , tick is " + latestTicks.get(pair));
        }

        System.out.println("Ticks contains: ");
        for (Pair pair : memBuff.getTicks().keySet()) {
            if(memBuff.getTicks().containsKey(pair)) {
                System.out.println("Pair: " + pair);
                for (Tick tick : memBuff.getTicks().get(pair)) {
                    System.out.println("tick: " + tick);
                }
            }
        }
    }

    public void TEST_testMessageArrived() {
        MockConnection conn = new MockConnection();
        
    }
}
